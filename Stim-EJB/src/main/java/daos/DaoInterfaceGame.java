/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import modelo.Game;
import modelo.Genre;
import modelo.OS;

/**
 *
 * @author MULTI
 */
public interface DaoInterfaceGame extends Dao<Game> {

    Collection<Game> findAllByGenre(Genre genre) throws SQLException;

    Collection<Genre> findGameGenre(Long gameId) throws SQLException;

    Collection<OS> findGameOs(Long gameId) throws SQLException;

    Collection<Game> findAll() throws SQLException;

    Collection<Genre> findAllGenres() throws SQLException;

    Collection<OS> findAllOS() throws SQLException;

    Optional<Game> findById(Long id) throws SQLException;

    void insert(Game entity) throws SQLException;

    int update(Game entity) throws SQLException;

    int delete(Long id) throws SQLException;
}
