/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejbs;

import daos.DaoInterfaceGame;
import javax.sql.DataSource;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Game;
import modelo.Genre;
import modelo.OS;

/**
 *
 * @author MULTI
 */
@Stateful
public class GameEJB implements GameLocal {

    @EJB
    private DaoInterfaceGame gameDao;

    @Override
    public void addGame(Game game) {
        try {
            gameDao.insert(game);
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editGame(Game game) {
        try {
            gameDao.update(game);
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteGame(Game game) {
        try {
            gameDao.delete(game.getId());
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<Game> findAll() {
        List<Game> lista = new ArrayList<>();
        try {
            lista = (List<Game>) gameDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Collection<Genre> findGameGenre(Long gameId) {
        List<Genre> lista = new ArrayList<>();
        try {
            lista = (List<Genre>) gameDao.findGameGenre(gameId);
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Collection<OS> findGameOS(Long gameId) {
        List<OS> lista = new ArrayList<>();
        try {
            lista = (List<OS>) gameDao.findGameOs(gameId);
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Collection<Genre> findAllGenres() {
        List<Genre> lista = new ArrayList<>();
        try {
            lista = (List<Genre>) gameDao.findAllGenres();
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Collection<OS> findAllOS() {
        List<OS> lista = new ArrayList<>();
        try {
            lista = (List<OS>) gameDao.findAllOS();
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Optional<Game> findById(Long id) {
        try {
            return gameDao.findById(id);
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
    }
}
