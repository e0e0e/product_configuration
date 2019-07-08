package pl.sda.springdemo.video;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController {

    VideoService videoService = new VideoService();

    @GetMapping(value = "/video/{id}")
    public String getVideo(@PathVariable Long id, Model model) {
        Video video = videoService.findVideo(id);
        model.addAttribute("video", video);

        return "video/details";
    }

    @GetMapping(value = "/video")
    public String getVideos(@RequestParam(required = false) String phrase,
                            @RequestParam(required = false) String sort,
                            Model model) {

        model.addAttribute("videos", videoService.findVideos(phrase, sort));
        model.addAttribute("sort", "DESC".equals(sort) ? "ASC" : "DESC");

        return "video/list";
    }

    @GetMapping(value = "/video/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("video", videoService.findVideo(id));
        model.addAttribute("categories", VideoService.VALID_CATEGORIES);

        return "video/edit";
    }

    @PostMapping(value = "/video/{id}/save",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@PathVariable Long id,
                       @RequestParam String title,
                       @RequestParam String category,
                       @RequestParam int rating,
                       Model model) {
        try {
            videoService.edit(id, title, category, rating);
            model.addAttribute("videos", videoService.findVideos());

            return "video/list";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("video", videoService.findVideo(id));
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("categories", VideoService.VALID_CATEGORIES);

            return "video/edit";
        }
    }

    @GetMapping(value = "/video/add")
    public String add(Model model) {
        model.addAttribute("categories", VideoService.VALID_CATEGORIES);

        return "video/edit";
    }

    @PostMapping(value = "/video",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addVideo(
        @RequestParam String title,
        @RequestParam String category,
        @RequestParam int rating,
        Model model) {
        videoService.add(title, category, rating);
        model.addAttribute("videos", videoService.findVideos());

        return "video/list";
    }

    @GetMapping(value = "/video/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        videoService.delete(id);
        model.addAttribute("videos", videoService.findVideos());

        return "video/list";
    }
}
