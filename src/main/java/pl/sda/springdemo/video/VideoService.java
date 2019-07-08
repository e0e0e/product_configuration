package pl.sda.springdemo.video;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class VideoService {

    final static List<String> VALID_CATEGORIES = Arrays.asList(
        "Action", "Horror", "Thriller", "XXX", "Adventure"
    );
    private final static Map<Long, Video> videoMap = new HashMap<>();

    static {
        videoMap.put(1L, new Video(1L, "Die Hard", "Action", new Date(), 7));
        videoMap.put(2L, new Video(2L, "Rocky", "Action", new Date(), 6));
        videoMap.put(3L, new Video(3L, "The Thing", "Horror", new Date(), 5));
    }

    public void edit(long id, String title, String category, int rating) {
        validateTitle(title);
        validateCategory(category);
        validateRating(rating);

        Video current = videoMap.get(id);

        current.setTitle(title);
        current.setCategory(category);
        current.setRating(rating);
    }

    private void validateRating(int rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Invalid rating value");
        }
    }

    private void validateCategory(String category) {
        if (!VALID_CATEGORIES.contains(category)) {
            throw new IllegalArgumentException("Invalid category name");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
    }

    public Video findVideo(Long id) {
        return videoMap.get(id);
    }

    public Collection<Video> findVideos() {
        return videoMap.values();
    }

    public void add(String title, String category, int rating) {
        Long id = videoMap.entrySet().stream()
            .map(Entry::getKey)
            .max(Long::compareTo)
            .map(cId -> cId + 1)
            .orElse(1L);

        validateTitle(title);
        validateCategory(category);
        validateRating(rating);

        Video newVideo = new Video(id, title, category, new Date(), rating);
        videoMap.put(newVideo.getId(), newVideo);
    }

    public void delete(Long id) {
        videoMap.remove(id);
    }

    public Collection<Video> findVideos(String phrase, String sort) {
        Collection<Video> sortedVideos = sortVideos(sort);
        if (phrase != null) {
            return sortedVideos.stream()
                .filter(video -> video.getTitle().toLowerCase().contains(phrase.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            return sortedVideos;
        }
    }

    private Collection<Video> sortVideos(String sort) {
        Comparator<Video> asc = Comparator.comparing(Video::getTitle);
        Comparator<Video> desc = asc.reversed();

        return videoMap.values().stream()
            .sorted("DESC".equals(sort) ? desc : asc)
            .collect(Collectors.toList());
    }
}
