/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejbs;

import daos.DaoInterfaceGame;
import daos.DaoInterfaceReview;
import daos.DaoInterfaceUser;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Game;
import modelo.Review;
import modelo.ReviewId;
import modelo.User;

/**
 *
 * @author MULTI
 */
@Stateless
public class ReviewEJB implements ReviewLocal {

    @EJB
    private DaoInterfaceReview reviewDao;
    @EJB
    private DaoInterfaceGame gameDao;
    @EJB
    private DaoInterfaceUser userDao;

    @Override
    public void addReview(Review review) {
        try {
            reviewDao.insert(review);

            User user = review.getUser();
            user.setReviews(user.getReviews() + 1);
            userDao.update(user);

            Game game = review.getGame();
            Collection<Review> allReviews = reviewDao.findByGame(game.getId());

            int totalReviews = allReviews.size();
            double sumStars = allReviews.stream().mapToDouble(Review::getRating).sum();

            game.setCountReview(totalReviews);
            game.setRating((double) Math.round(sumStars / totalReviews));

            gameDao.update(game);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editReview(Review review) {
        try {
            reviewDao.update(review);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteReview(Review review) {
        try {
            ReviewId rid = review.getId();
            Review reviewPersistent = reviewDao.findById(rid).orElse(null);

            if (reviewPersistent != null) {
                User user = reviewPersistent.getUser();
                Long gameId = reviewPersistent.getGame().getId();

                reviewDao.delete(rid);

                if (user != null) {
                    int current = (user.getReviews() != null) ? user.getReviews() : 1;
                    user.setReviews(Math.max(0, current - 1));
                    userDao.update(user);
                }

                actualizarEstadisticasJuego(gameId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<Review> findAll() {
        try {
            return reviewDao.findAll();
        } catch (SQLException e) {
            Logger.getLogger(ReviewEJB.class.getName())
                    .log(Level.SEVERE, null, e);
            return List.of();
        }
    }

    @Override
    public Collection<Review> findByGame(Long gameId) {
        return reviewDao.findByGame(gameId);
    }

    private void actualizarEstadisticasJuego(Long gameId) throws SQLException {
        java.util.Optional<Game> gameOpt = gameDao.findById(gameId);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            Collection<Review> allReviews = reviewDao.findByGame(gameId);

            int total = allReviews.size();
            double finalRating = 1.0;

            if (total > 0) {
                double sum = allReviews.stream().mapToDouble(r -> r.getRating() != null ? r.getRating() : 1).sum();

                double average = sum / total;
                finalRating = Math.max(1.0, Math.min(5.0, Math.round(average)));
            }

            game.setCountReview(total);
            game.setRating(finalRating);
            gameDao.update(game);
        }
    }
}
