package pl.sda.springdemo.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;


    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public String ShowProjectForm(Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/project";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam Long user,
                             Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }

        model.addAttribute("loggedUser", userService.getLogged());


        try {

            //dodanie usera
            boolean result = projectService.create(projectName, description, userService.findById(user));


            model.addAttribute("createProjectResult", result);
            List<Project> projects = projectService.findAll();


//            userService.findById(user).setProjectsParticipants(projects);

            model.addAttribute("projects", projects);

            //System.out.println(email + " " + password + " " + datfB);
            return "users/projectList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());

            return "users/project";
        }
//
//
//        System.out.println(projectName + " | " + description + " | " + user);
//        return "users/project";
    }

    @GetMapping("users/projectList")
    public String listProjects(Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }
        List<Project> projects = projectService.findAll();

//        System.out.println(projectList.size());
        model.addAttribute("projects", projects);
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/projectList";
    }

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam long projectId,
                                Model model) {

        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("deleteProjectResults", true);
        model.addAttribute("loggedUser", userService.getLogged());

        return "users/projectList";

    }

    @GetMapping("participants")
    public String addParticipant(Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/participants";
    }

    @PostMapping("project/participant")
    public String addParticipantToProject(@RequestParam long projectId,
                                          @RequestParam long userId,
                                          Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }
        // model.addAttribute("addingToProjectID",projectService.findById(projectId));
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId).get();
        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/projectList";
    }

    @GetMapping("login")
    public String login() {

        return "users/login";
    }

    @GetMapping("/")
    public String start() {

        if (userService.getLogged() == null) {
            return "users/login";
        } else {
            return "users/projectList";
        }
    }

    @PostMapping("login")
    public String loginUser(@RequestParam String userName,
                            @RequestParam String password,
                            Model model) {
        userService.login(userName, password);

        model.addAttribute("loggedUser", userService.getLogged());
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("logout")
    public String logoutUser(Model model) {
        userService.logout();


        return "users/login";
    }

    @GetMapping("sprint")
    public String addSprintForm() {

        return "users/sprint";
    }

    @PostMapping("sprint")
    public String addSprint(@RequestParam String from,
                            @RequestParam String to,
                            @RequestParam Integer storyPoints,
                            Model model) {
        if (userService.getLogged() == null) {
            return "users/login";
        }

        model.addAttribute("loggedUser", userService.getLogged());
        userService.saveSprint(LocalDate.parse(from), LocalDate.parse(to), storyPoints);

        return "users/sprintList";
    }
}