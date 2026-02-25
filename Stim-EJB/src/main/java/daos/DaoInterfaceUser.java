/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import jakarta.ejb.Local;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import modelo.Review;
import modelo.User;

/**
 *
 * @author MULTI
 */
@Local
public interface DaoInterfaceUser extends Dao<User> {

    Collection<User> findAll() throws SQLException;

    Collection<Review> findAllReviews(User user);

    Optional<User> findById(Long id) throws SQLException;

    User findByEmail(String email) throws SQLException;

    void insert(User entity) throws SQLException;

    int update(User entity) throws SQLException;

    int delete(Long id) throws SQLException;
}
