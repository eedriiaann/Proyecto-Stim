/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdibeans;

import ejbs.GameLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import modelo.Game;
import modelo.Genre;
import modelo.OS;

/**
 *
 * @author MULTI
 */
@Named(value = "gameBean")
@SessionScoped
public class GameBean implements Serializable {

    @EJB
    private GameLocal gameEJB;
    private Game gameActual;

    private Collection<Genre> genres;
    private Collection<OS> os;

    public GameBean() {
    }

    public List<Game> findAll() {
        return (List<Game>) gameEJB.findAll();
    }

    public String viewGame(Game game) {
        gameActual = game;
        genres = gameEJB.findGameGenre(game.getId());
        os = gameEJB.findGameOS(game.getId());

        return "game";
    }

    public Game getGameActual() {
        return gameActual;
    }

    public void setGameActual(Game gameActual) {
        this.gameActual = gameActual;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    public Collection<OS> getOs() {
        return os;
    }

    public void setOs(Collection<OS> os) {
        this.os = os;
    }

    public void refreshGameActual() {
        if (this.gameActual != null) {
            this.gameActual = gameEJB.findById(this.gameActual.getId()).orElse(null);
        }
    }
}
