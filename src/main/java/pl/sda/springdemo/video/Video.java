package pl.sda.springdemo.video;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Video {

    private Long id;
    private String title;
    private String category;
    private Date releaseDate;
    private int rating;

    @JsonCreator
    public Video(Long id, String title, String category, Date releaseDate, int rating) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
