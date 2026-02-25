package modelo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewId implements Serializable {

    private Long idGame;
    private Long idUser;

    public ReviewId() {
    }

    public ReviewId(Long idGame, Long idUser) {
        this.idGame = idGame;
        this.idUser = idUser;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewId)) {
            return false;
        }
        ReviewId that = (ReviewId) o;
        return Objects.equals(idGame, that.idGame)
                && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGame, idUser);
    }
}
