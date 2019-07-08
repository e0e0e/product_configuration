package pl.sda.springdemo.video;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateVideo {

    private String title;
    private String category;
    private int rating;

    @JsonCreator
    public UpdateVideo(String title, String category, int rating) {
        this.title = title;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }
}
