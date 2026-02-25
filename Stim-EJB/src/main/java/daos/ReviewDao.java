package daos;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import modelo.Review;
import modelo.ReviewId;
import modelo.User;

@Stateless
public class ReviewDao implements DaoInterfaceReview {

    @PersistenceContext(unitName = "gamesPU")
    private EntityManager em;

    @Override
    public Collection<Review> findAll() throws SQLException {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r", Review.class);
        return query.getResultList();
    }

    @Override
    public Optional<Review> findById(ReviewId id) throws SQLException {
        Review review = em.find(Review.class, id);
        return Optional.ofNullable(review);
    }

    @Override
    public void insert(Review review) throws SQLException {
        em.persist(review);
    }

    @Override
    public int update(Review review) throws SQLException {
        em.merge(review);
        return 1;
    }

    @Override
    public int delete(ReviewId id) throws SQLException {
        Review review = em.find(Review.class, id);
        if (review != null) {
            em.remove(review);
            return 1;
        }
        return 0;
    }

    public User getReviewCreator(Review review) {
        return review.getUser();
    }

    @Override
    public Collection<Review> findByGame(Long gameId) {
        return em.createQuery(
                "SELECT r FROM Review r WHERE r.game.id = :gameId",
                Review.class
        ).setParameter("gameId", gameId)
                .getResultList();
    }

    public int delete(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Review> findById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
