package pl.sda.springdemo.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {
    private final UserService userService;

    public ProjectController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/project")
    public String ShowProjectForm(Model model){
        model.addAttribute("users", userService.findAll());

        return "users/project";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                              @RequestParam String  description,
                              @RequestParam String user,
                              Model model){

        System.out.println(projectName+" | "+description+" | "+user);
        return "users/project";
    }
}
