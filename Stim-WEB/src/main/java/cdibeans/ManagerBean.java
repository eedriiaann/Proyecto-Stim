package cdibeans;

import ejbs.GameLocal;
import ejbs.UserLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Game;
import modelo.Genre;
import modelo.OS;
import modelo.User;

@Named("managerBean")
@ViewScoped
public class ManagerBean implements Serializable {

    @EJB
    private UserLocal userEJB;

    @EJB
    private GameLocal gameEJB;

    private User selectedUser;
    private Game selectedGame;

    private List<Genre> allGenres;
    private List<OS> allOS;

    private List<String> selectedGenreIds;
    private List<String> selectedOsIds;

    @PostConstruct
    public void init() {
        selectedUser = new User();
        selectedGame = new Game();
        selectedGenreIds = new ArrayList<>();
        selectedOsIds = new ArrayList<>();
        loadGenres();
        loadOS();
    }

    private void loadGenres() {
        allGenres = (List<Genre>) gameEJB.findAllGenres();
        System.out.println("GENRES CARGADOS: " + allGenres.size());
    }

    private void loadOS() {
        allOS = (List<OS>) gameEJB.findAllOS();
        System.out.println("OS CARGADOS: " + allOS.size());
    }

    // Usuarios
    public Collection<User> findAllUsers() {
        return userEJB.findAll();
    }

    public void prepareNewUser() {
        selectedUser = new User();
    }

    public void prepareEditUser(User user) {
        selectedUser = user;
    }

    public void saveUser() {
        if (selectedUser.getId() == null) {
            userEJB.addUser(selectedUser);
        } else {
            userEJB.editUser(selectedUser);
        }
        selectedUser = new User();
    }

    public void deleteUser(User user) {
        userEJB.deleteUser(user);
    }

    // Juegos
    public Collection<Game> findAllGames() {
        return gameEJB.findAll();
    }

    public void prepareNewGame() {
        selectedGame = new Game();
        selectedGame.setRating(0D);
        selectedGame.setCountReview(0);
        selectedGenreIds.clear();
        selectedOsIds.clear();
    }

    public void prepareEditGame(Game game) {
        selectedGame = game;

        if (game.getGenres() != null) {
            selectedGenreIds = game.getGenres().stream()
                    .map(g -> String.valueOf(g.getId()))
                    .collect(Collectors.toList());
        }

        if (game.getOsList() != null) {
            selectedOsIds = game.getOsList().stream()
                    .map(os -> String.valueOf(os.getId()))
                    .collect(Collectors.toList());
        }
    }

    public void saveGame() {
        List<Genre> generosSeleccionados = new ArrayList<>();
        Collection<Genre> todosLosGeneros = gameEJB.findAllGenres();

        for (Object idObj : selectedGenreIds) {
            Integer id = Integer.valueOf(idObj.toString());
            todosLosGeneros.stream().filter(g -> g.getId().equals(id)).findFirst().ifPresent(generosSeleccionados::add);
        }
        selectedGame.setGenres(generosSeleccionados);

        List<OS> osSeleccionados = new ArrayList<>();
        Collection<OS> todosLosOS = gameEJB.findAllOS();

        for (Object idObj : selectedOsIds) {
            Integer id = Integer.valueOf(idObj.toString());
            todosLosOS.stream().filter(os -> os.getId().equals(id)).findFirst().ifPresent(osSeleccionados::add);
        }
        selectedGame.setOsList(osSeleccionados);

        if (selectedGame.getId() == null) {
            gameEJB.addGame(selectedGame);
        } else {
            gameEJB.editGame(selectedGame);
        }

    }

    public void deleteGame(Game game) {
        gameEJB.deleteGame(game);
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public Game getSelectedGame() {
        return selectedGame;
    }

    public List<Genre> getAllGenres() {
        return allGenres;
    }

    public List<OS> getAllOS() {
        return allOS;
    }

    public List<String> getSelectedGenreIds() {
        return selectedGenreIds;
    }

    public void setSelectedGenreIds(List<String> selectedGenreIds) {
        this.selectedGenreIds = selectedGenreIds;
    }

    public List<String> getSelectedOsIds() {
        return selectedOsIds;
    }

    public void setSelectedOsIds(List<String> selectedOsIds) {
        this.selectedOsIds = selectedOsIds;
    }
}
