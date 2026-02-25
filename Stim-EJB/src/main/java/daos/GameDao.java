package daos;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import modelo.Game;
import modelo.Genre;
import modelo.OS;

@Stateless
public class GameDao implements DaoInterfaceGame {

    @PersistenceContext(unitName = "gamesPU")
    private EntityManager em;

    @Override
    public Collection<Game> findAll() throws SQLException {
        return em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }

    @Override
    public Optional<Game> findById(Long id) throws SQLException {
        return Optional.ofNullable(em.find(Game.class, id));
    }

    @Override
    public void insert(Game entity) throws SQLException {
        em.persist(entity);
    }

    @Override
    public int update(Game entity) throws SQLException {
        em.merge(entity);
        return 1;
    }

    @Override
    public int delete(Long id) throws SQLException {
        Game entity = em.find(Game.class, id);
        if (entity != null) {
            em.remove(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Game> findAllByGenre(Genre genre) {
        return em.createQuery(
                "SELECT g FROM Game g JOIN g.genres gen WHERE gen.id = :genreId", Game.class
        ).setParameter("genreId", genre.getId()).getResultList();
    }

    @Override
    public List<Genre> findGameGenre(Long gameId) {
        return em.createQuery(
                "SELECT gen FROM Game g JOIN g.genres gen WHERE g.id = :gameId",
                Genre.class
        ).setParameter("gameId", gameId)
                .getResultList();
    }

    @Override
    public List<OS> findGameOs(Long gameId) {
        return em.createQuery(
                "SELECT os FROM Game g JOIN g.osList os WHERE g.id = :gameId",
                OS.class
        )
                .setParameter("gameId", gameId)
                .getResultList();
    }

    @Override
    public Collection<Genre> findAllGenres() {
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public Collection<OS> findAllOS() {
        return em.createQuery("SELECT o FROM OS o", OS.class).getResultList();
    }

}
