package pl.sda.pms.projects;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.sda.pms.task.Task;
import pl.sda.pms.task.TaskService;
import pl.sda.pms.users.User;
import pl.sda.pms.users.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;
private ServletContext servletContext;

    public ProjectController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/project")
    public String ShowProjectForm(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "Add Project");
        model.addAttribute("path", "project/project");

        return "main";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam String username,
                             Model model) {



        User user = userService.findUserByname(username);

        try {

            boolean result = projectService.create(projectName, description, user);


            model.addAttribute("createProjectResult", result);
            List<Project> projects = projectService.findAll();



            model.addAttribute("projects", projects);


            return "redirect:users/projectList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());

            return "user/project";
        }

    }

    @GetMapping("users/projectList")
    public String listProjects(HttpServletRequest request,
                               HttpServletResponse  response,
                               Model model) {

        String loggedUserName = request.getRemoteUser();

        List<Project> projects = projectService.findAllWhereAdmin(loggedUserName);
        List<Project> projectsWhereParticipate = projectService.findAllWhereParticipate(loggedUserName);

        model.addAttribute("projectsWhereParticipate", projectsWhereParticipate);
        model.addAttribute("projects", projects);

        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/projectList");
        return "main";
    }

    @GetMapping("users/allProjectList")
    public String listAllProjects(
            Model model) {

        List<Project> projects = projectService.findAll();


        model.addAttribute("projects", projects);

        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/allProjectList");
        return "main";
    }

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam long projectId,
                                Model model) {

        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("deleteProjectResults", true);

        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/projectList");
        return "main";

    }

    @PostMapping("/participant")
    public String addParticipant(@RequestParam long projectId,
                                 @RequestParam(required = false) String filterUserByEmail,
                                 HttpServletRequest request,
                                 Model model) {

        String loggedUserName = request.getRemoteUser();

        model.addAttribute("projects", projectService.findById(projectId).get());
        if (filterUserByEmail == null) {
            model.addAttribute("users", userService.findAllWithException(loggedUserName));/**/
        } else {
            model.addAttribute("users", userService.findUsersByEmailWithException(filterUserByEmail, loggedUserName));
        }

        model.addAttribute("title", "Participants");
        model.addAttribute("path", "participant/participants");
        return "main";
    }


    @PostMapping(value = "project/participant", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addParticipantToProject(@RequestParam long projectId,
                                          @RequestParam long userId,
                                          Model model) {

        User user = userService.findById(userId);
        Project project = projectService.findById(projectId).get();
        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", projectService.getUsers(projectId));



        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/projectList");
        return "main";
    }


    @GetMapping("login")
    public String login() {

        return "user/login";
    }


    @GetMapping("logout")
    public String logoutUser(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        return "user/login";
    }

    @GetMapping("/project/show")
    public String showProject(@RequestParam Long projectId,
                              Model model) {

        Project project = projectService.findById(projectId).get();

        List<Task> taskList = project.getTask().stream()
                .sorted(Comparator.comparing(e -> e.getSprint().getStartDate()))
                .collect(Collectors.toList());

        List<Task> tasksToDo = taskService.findTasksToDo(projectId, 0);
        List<Task> tasksInProgress = taskService.findTasksToDo(projectId, 1);
        List<Task> tasksDone = taskService.findTasksToDo(projectId, 2);

        model.addAttribute("tasksToDo", tasksToDo);
        model.addAttribute("tasksInProgress", tasksInProgress);
        model.addAttribute("tasksDone", tasksDone);
        model.addAttribute("tasks", taskList);

        model.addAttribute("project", project);
        model.addAttribute("title", "Show Project");
        model.addAttribute("path", "project/showProject");
        return "main";
    }

    @GetMapping("/project/edit")
    public String editProject(@RequestParam Long projectId) {

        return null;
    }

//    @RequestMapping(value = "/image", method = RequestMethod.GET)
//    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
//        final InputStream in = servletContext.getResourceAsStream("/resources/images/icons/png/boy.png");
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//        IOUtils.copy(in, response.getOutputStream());
//    }


}