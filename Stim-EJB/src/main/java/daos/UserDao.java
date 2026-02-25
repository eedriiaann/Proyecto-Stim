package daos;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import modelo.Review;
import modelo.User;

@Stateless
@Local(DaoInterfaceUser.class)
public class UserDao implements DaoInterfaceUser {

    @PersistenceContext(unitName = "gamesPU")
    private EntityManager em;

    @Override
    public Collection<User> findAll() throws SQLException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void insert(User entity) throws SQLException {
        em.persist(entity);
    }

    @Override
    public int update(User entity) throws SQLException {
        em.merge(entity);
        em.flush();
        return 1;
    }

    @Override
    public int delete(Long id) throws SQLException {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Review> findAllReviews(User user) {
        TypedQuery<Review> query = em.createQuery(
                "SELECT r FROM Review r WHERE r.user.id = :userId", Review.class
        );
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
