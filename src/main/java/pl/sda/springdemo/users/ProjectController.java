package pl.sda.springdemo.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("users", userService.findAll());

        return "users/project";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam Long user,
                             Model model) {
        try {

            //dodanie usera
            boolean result = projectService.create( projectName,  description, userService.findById(user));


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
        List<Project> projects = projectService.findAll();

//        System.out.println(projectList.size());
        model.addAttribute("projects", projects);

        return "users/projectList";
    }

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam long projectId,
                             Model model) {

        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("deleteProjectResults", true);

        return "users/projectList";

    }

    @GetMapping("participants")
    public String addParticipant(Model model){
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users",userService.findAll());
    return "users/participants";
    }

    @PostMapping("project/participant")
    public String addParticipantToProject(@RequestParam long projectId,
                                          @RequestParam long userId,
                                          Model model){
       // model.addAttribute("addingToProjectID",projectService.findById(projectId));
        User user=userService.findById(userId);
        Project project=projectService.findById(projectId).get();
        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users",userService.findAll());
        return "users/projectList";
    }
}