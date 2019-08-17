package pl.sda.springdemo.sprint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class SprintController {
    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("sprint")
    public String addSprintForm(Model model) {
        model.addAttribute("title","Add Sprint");
        model.addAttribute("path","sprint/sprint");

        return "main";
    }
    @GetMapping("sprintList")
    public String showSprintList(Model model) {


//        model.addAttribute("loggedUser", userService.getLogged());
        // System.out.println("eee user: "+userService.findUserByName("eee").getUsername());
        model.addAttribute("sprints", sprintService.findAllSprints());
        return "sprint/sprintList";
    }

    @PostMapping("sprint")
    public String addSprint(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
                            @RequestParam Integer storyPoints,
                            Model model) {



        sprintService.saveSprint(from, to, storyPoints);
        model.addAttribute("sprints", sprintService.findAllSprints());
        return "sprint/sprintList";
    }
}
