package pl.sda.pms.sprint;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Controller
public class SprintController {
    private SprintService sprintService;
    private ServletContext servletContext;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("sprint")
    public String addSprintForm(HttpServletRequest request,
                                Model model) {


        model.addAttribute("title", "Add Sprint");
        model.addAttribute("path", "sprint/sprint");

        return "main";
    }


    @GetMapping("sprintList")
    public String showSprintList(Model model) {


        model.addAttribute("sprints", sprintService.findAllSprints());

        model.addAttribute("title", "Sprint List");
        model.addAttribute("path", "sprint/sprintList");

        return "main";
    }

    @PostMapping("sprint")
    public String addSprint(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
                            @RequestParam Integer storyPoints,
                            Model model) {


        try {
            sprintService.saveSprint(from, to, storyPoints);
        } catch (Exception e) {

            model.addAttribute("errorMessage", e.getLocalizedMessage());
            model.addAttribute("title", "Add Sprint");
            model.addAttribute("path", "sprint/sprint");

            return "main";
        }
        model.addAttribute("sprints", sprintService.findAllSprints());
        model.addAttribute("title", "Sprint List");
        model.addAttribute("path", "sprint/sprintList");

        return "main";
    }

    @GetMapping("sprint/delete")
    public String deleteSprint(@RequestParam long sprintId,
                               Model model) {
        sprintService.deleteSprint(sprintId);

        return "redirect:/sprintList";
    }
}
