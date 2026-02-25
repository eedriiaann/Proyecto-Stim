/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejbs;

import jakarta.ejb.Local;
import java.util.Collection;
import modelo.Game;
import modelo.Review;

/**
 *
 * @author MULTI
 */
@Local
public interface ReviewLocal {

    public void addReview(Review review);

    public void editReview(Review review);

    public void deleteReview(Review review);

    Collection<Review> findAll();

    Collection<Review> findByGame(Long gameId);
}
