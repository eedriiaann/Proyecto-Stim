/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import modelo.Review;
import modelo.ReviewId;

/**
 *
 * @author MULTI
 */
public interface DaoInterfaceReview extends Dao<Review> {

    Collection<Review> findAll() throws SQLException;

    Collection<Review> findByGame(Long gameId);

    Optional<Review> findById(ReviewId id) throws SQLException;

    void insert(Review entity) throws SQLException;

    int update(Review entity) throws SQLException;

    int delete(ReviewId rid) throws SQLException;
}
