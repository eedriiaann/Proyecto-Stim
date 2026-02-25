/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejbs;

import jakarta.ejb.Local;
import java.util.Collection;
import java.util.Optional;
import modelo.Game;
import modelo.Genre;
import modelo.OS;

/**
 *
 * @author MULTI
 */
@Local
public interface GameLocal {

    public void addGame(Game game);

    public void editGame(Game game);

    public void deleteGame(Game game);

    Collection<Game> findAll();

    Optional<Game> findById(Long id);

    Collection<Genre> findAllGenres();

    Collection<OS> findAllOS();

    Collection<Genre> findGameGenre(Long gameId
    );

    Collection<OS> findGameOS(Long gameId
    );
}
