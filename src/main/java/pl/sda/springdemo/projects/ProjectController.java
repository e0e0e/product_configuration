package pl.sda.springdemo.projects;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
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
            return "user/login";
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.getLogged());
        return "project/project";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam Long user,
                             Model model) {
        if (userService.getLogged() == null) {
            return "user/login";
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
            return "project/projectList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());

            return "user/project";
        }
//
//
//        System.out.println(projectName + " | " + description + " | " + user);
//        return "users/project";
    }

    @GetMapping("users/projectList")
    public String listProjects(Model model) {
        if (userService.getLogged() == null) {
            return "user/login";
        }
        List<Project> projects = projectService.findAll();

//        System.out.println(projectList.size());
        model.addAttribute("projects", projects);
        model.addAttribute("loggedUser", userService.getLogged());
        return "project/projectList";
    }

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam long projectId,
                                Model model) {

        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("deleteProjectResults", true);
        model.addAttribute("loggedUser", userService.getLogged());

        return "project/projectList";

    }

    @GetMapping("/participant")
    public String addParticipant(@RequestParam long projectId,
                                 Model model) {
        if (userService.getLogged() == null) {
            return "user/login";
        }
        model.addAttribute("projects", projectService.findById(projectId).get());
        model.addAttribute("users", userService.findAll());/**/
        model.addAttribute("loggedUser", userService.getLogged());
        return "participant/participants";
    }

    @PostMapping(value = "project/participant", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addParticipantToProject(@RequestParam long projectId,
                                          @RequestParam long userId,
                                          Model model) {
        if (userService.getLogged() == null) {
            return "user/login";
        }
        // model.addAttribute("addingToProjectID",projectService.findById(projectId));
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId).get();
        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", projectService.getUsers(projectId));
        model.addAttribute("loggedUser", userService.getLogged());
        return "project/projectList";
    }

    @GetMapping("login")
    public String login() {

        return "user/login";
    }

    @GetMapping("/")
    public String start(@CookieValue(value = "userName", defaultValue = "") String userName,
                        @CookieValue(value = "password", defaultValue = "") String password,
                        Model model) {


        if (userService.getLogged() == null) {
         userService.login(userName, password);
//            System.out.println(cookies.);
            model.addAttribute("loggedUser", userService.getLogged());
            model.addAttribute("users", userService.findAll());
            return "users/list";
        } else {
            return "project/projectList";
        }
    }

    @PostMapping("login")
    public String loginUser(@RequestParam String userName,
                            @RequestParam String password,
                            HttpServletResponse response,
                            Model model) {

        userService.login(userName, password);
        Cookie cookie=new Cookie("userName", userName);
        Cookie cookie2=new Cookie("password", password);
        response.addCookie(cookie);
        response.addCookie(cookie2);

        model.addAttribute("loggedUser", userService.getLogged());
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("logout")
    public String logoutUser(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        userService.logout();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
             Arrays.stream(cookies)
                    .forEach(c -> c.setMaxAge(0));
//            response.addCookie();
        }
        return "user/login";
    }

    @GetMapping("sprint")
    public String addSprintForm() {

        return "sprint/sprint";
    }
    @GetMapping("sprintList")
    public String showSprintList(Model model) {

        if (userService.getLogged() == null) {
            return "user/login";
        }

        model.addAttribute("loggedUser", userService.getLogged());
       // System.out.println("eee user: "+userService.findUserByName("eee").getUsername());
        model.addAttribute("sprints", userService.findAllSprints());
        return "sprint/sprintList";
    }

    @PostMapping("sprint")
    public String addSprint(@RequestParam String from,
                            @RequestParam String to,
                            @RequestParam Integer storyPoints,
                            Model model) {
        if (userService.getLogged() == null) {
            return "user/login";
        }

        model.addAttribute("loggedUser", userService.getLogged());
        userService.saveSprint(LocalDate.parse(from), LocalDate.parse(to), storyPoints);
        model.addAttribute("sprints", userService.findAllSprints());
        return "sprint/sprintList";
    }
}