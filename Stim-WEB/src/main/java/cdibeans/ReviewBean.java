/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdibeans;

import ejbs.ReviewLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import modelo.Game;
import modelo.Review;
import modelo.ReviewId;
import modelo.User;

/**
 *
 * @author MULTI
 */
@Named(value = "reviewBean")
@ViewScoped
public class ReviewBean implements Serializable {

    @EJB
    ReviewLocal reviewEJB;
    @Inject
    private GameBean gameBean;
    @Inject
    private UserBean userBean;

    private List<Review> reviews;
    private Review newReview;

    @PostConstruct
    public void init() {
        reviews = (List<Review>) reviewEJB.findByGame(gameBean.getGameActual().getId());
        newReview = new Review();
    }

    public ReviewBean() {
    }

    public void addReview() {

        Game game = gameBean.getGameActual();
        User user = userBean.getUserActual();

        ReviewId id = new ReviewId();
        id.setIdGame(game.getId());
        id.setIdUser(user.getId());

        newReview.setId(id);
        newReview.setGame(game);
        newReview.setUser(user);
        newReview.setReviewDate(new Date());

        reviewEJB.addReview(newReview);

        gameBean.setGameActual(game);
        userBean.setUserActual(user);

        newReview = new Review();
        reviews = (List<Review>) reviewEJB.findByGame(gameBean.getGameActual().getId());
    }

    public void removeReview(Review reviewToDelete) {
        reviewEJB.deleteReview(reviewToDelete);
        gameBean.refreshGameActual();
        this.reviews = (List<Review>) reviewEJB.findByGame(gameBean.getGameActual().getId());
    }

    public void resetNewReview() {
        newReview = new Review();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Review getNewReview() {
        return newReview;
    }

    public void setNewReview(Review newReview) {
        this.newReview = newReview;
    }

    public GameBean getGameBean() {
        return gameBean;
    }

    public void setGameBean(GameBean gameBean) {
        this.gameBean = gameBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

}
