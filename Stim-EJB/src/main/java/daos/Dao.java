/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author MULTI
 */
public interface Dao<T> {

    Collection<T> findAll() throws SQLException;

    Optional<T> findById(Long id) throws SQLException;

    void insert(T entitys) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(Long id) throws SQLException;
}
