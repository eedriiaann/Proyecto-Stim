package modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double rating;

    @Column(name = "count_review")
    private Integer countReview;

    private String developer;
    private String publisher;

    @Column(name = "launch_date")
    private LocalDate launchDate;

    @Column(name = "poster_uri")
    private String posterUri;

    @Column(name = "banner_uri")
    private String bannerUri;

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "id_game"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "game_os",
            joinColumns = @JoinColumn(name = "id_game"),
            inverseJoinColumns = @JoinColumn(name = "id_os")
    )
    private List<OS> osList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCountReview() {
        return countReview;
    }

    public void setCountReview(Integer countReview) {
        this.countReview = countReview;
        if (this.countReview == null) {
            this.countReview = 0;
        }
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getPosterUri() {
        return posterUri;
    }

    public void setPosterUri(String posterUri) {
        this.posterUri = posterUri;
    }

    public String getBannerUri() {
        return bannerUri;
    }

    public void setBannerUri(String bannerUri) {
        this.bannerUri = bannerUri;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<OS> getOsList() {
        return osList;
    }

    public void setOsList(List<OS> osList) {
        this.osList = osList;
    }

    public double getStars() {
        if (this.rating == null) {
            return 0.0;
        }
        return Math.round(this.rating * 2) / 2.0;
    }

    public int getRatingPercentage() {
        return (int) Math.round((getStars() / 5.0) * 100);
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
