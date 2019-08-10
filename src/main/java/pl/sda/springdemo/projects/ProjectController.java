package pl.sda.springdemo.projects;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.sprint.TimeTable;
import pl.sda.springdemo.task.Task;
import pl.sda.springdemo.task.TaskService;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;



    public ProjectController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/project")
    public String ShowProjectForm(HttpServletRequest request,
                                  Model model) {

        //request.getSession().setAttribute("username",);
        String loggedUser = request.getRemoteUser();
        System.out.println("you are logged as: " + loggedUser);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", loggedUser);
//        List<String> sessionVals = Collections.list(request.getSession().getAttributeNames());
//
//        sessionVals.stream().forEach(s -> System.out.println(s));
//        Object spring_security_context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        System.out.println(spring_security_context);

        model.addAttribute("title", "Add Project");
        model.addAttribute("path", "project/project");

        return "main";
    }

    @PostMapping("/project")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam Long user,
                             Model model) {


        // model.addAttribute("loggedUser", userService.getLogged());


        try {

            //dodanie usera
            boolean result = projectService.create(projectName, description, userService.findById(user));


            model.addAttribute("createProjectResult", result);
            List<Project> projects = projectService.findAll();


//            userService.findById(user).setProjectsParticipants(projects);

            model.addAttribute("projects", projects);

            //System.out.println(email + " " + password + " " + datfB);
            model.addAttribute("title", "Project List");
            model.addAttribute("path", "project/projectList");
            return "main";
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

        List<Project> projects = projectService.findAll();

//        System.out.println(projectList.size());
        model.addAttribute("projects", projects);
        model.addAttribute("loggedUser", userService.getLogged());
        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/projectList");
        return "main";
    }

    @GetMapping("project/delete")
    public String deleteProject(@RequestParam long projectId,
                                Model model) {

        projectService.delete(projectId);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("deleteProjectResults", true);
        model.addAttribute("loggedUser", userService.getLogged());

        model.addAttribute("title", "Project List");
        model.addAttribute("path", "project/projectList");
        return "main";

    }

    @PostMapping("/participant")
    public String addParticipant(@RequestParam long projectId,
                                 @RequestParam(required = false) String filterUserByEmail,
                                 Model model) {

        model.addAttribute("projects", projectService.findById(projectId).get());
        if(filterUserByEmail==null) {
            model.addAttribute("users", userService.findAll());/**/
        }else{
            model.addAttribute("users",userService.findUsersByEmail(filterUserByEmail));
        }
//        model.addAttribute("loggedUser", userService.getLogged());

        model.addAttribute("title", "Participants");
        model.addAttribute("path", "participant/participants");
        return "main";
    }


    @PostMapping(value = "project/participant", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addParticipantToProject(@RequestParam long projectId,
                                          @RequestParam long userId,
                                          Model model) {

        // model.addAttribute("addingToProjectID",projectService.findById(projectId));
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId).get();
        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", projectService.getUsers(projectId));
        model.addAttribute("loggedUser", userService.getLogged());


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
        userService.logout();

        return "user/login";
    }

    @GetMapping("/project/show")
    public String showProject(@RequestParam Long projectId,
                              Model model) {
        Project project = projectService.findById(projectId).get();
        Set<Task> taskList = project.getTask();

        List<Integer> numberOfDays = project.getTask().stream().map(e ->
                Math.abs(Period.between(e.getSprint().getStartDate(), e.getSprint().getFinishDate()).getDays()))
                .collect(Collectors.toList());


//        project.getTask().stream()
//                .collect(Collectors.toMap(x -> x.getId(), x -> x.getSprint().getStartDate()));
        if (project.getTask().size() > 0) {


            LocalDate maxFinishDate = project.getTask().stream().max(Comparator.comparing(e ->
                    e.getSprint().getFinishDate())).get().getSprint().getFinishDate().plusDays(1);
            LocalDate minStartDate = project.getTask().stream().min(Comparator.comparing(e ->
                    e.getSprint().getStartDate())).get().getSprint().getStartDate().plusDays(-1);

            List<String> timeLine = new ArrayList<>();
            for (LocalDate date = minStartDate; date.isBefore(maxFinishDate); date = date.plusDays(1)) {
                timeLine.add(date.format(DateTimeFormatter.ofPattern("MM/dd")));
            }


            Map<Long, TimeTable> timeTableMap = new HashMap<>();
            for (Task e : taskList) {
                long daysToStart = Math.abs(ChronoUnit.DAYS.between(e.getSprint().getStartDate(), minStartDate));
                long daysToFinish = Math.abs(ChronoUnit.DAYS.between(minStartDate, e.getSprint().getFinishDate()) + 1);
                long duration = Math.abs(ChronoUnit.DAYS.between(minStartDate, maxFinishDate));


                TimeTable timetable = new TimeTable(minStartDate, daysToStart, daysToFinish, duration, e.getId(), e);
                timeTableMap.put(timetable.getSprintId(), timetable);
                // System.out.println(daysToStart);

            }
            ;


//        Map<Long, TimeTable> sprintTable =
//                project.getTask().stream()
//                .map(e -> TimeTable(minStartDate,
//                        Math.abs(Period.between(e.getSprint().getStartDate(), minStartDate).getDays()),
//                        Math.abs(Period.between(e.getSprint().getFinishDate(), minStartDate).getDays()),
//                        Math.abs(Period.between(e.getSprint().getStartDate(), e.getSprint().getFinishDate()).getDays()),
//                        e.getId()))
//                        //.forEach(r-> System.out.println(r.toString()));
//                        .collect(Collectors.toMap(x -> x.getSprintId, x -> x));

            //        TimeTable(minStartDate,start,end,fromStartToFinish)

            // project.getTask().stream().m;
//        (e-> System.out.println(
//                Period.between(e.getSprint().getFinishDate(),e.getSprint().getStartDate()).getDays()));
//        timeLine.forEach(x -> System.out.println(x));
            model.addAttribute("timeLine", timeLine);
            model.addAttribute("timeTableMap", timeTableMap);
            model.addAttribute("maxFinishDate", maxFinishDate);
            model.addAttribute("minStartDate", minStartDate);

        }

        model.addAttribute("project", project);
        model.addAttribute("title", "Show Project");
        model.addAttribute("path", "project/showProject");
        return "main";
    }

    @GetMapping("/project/edit")
    public String editProject(@RequestParam Long projectId) {

        return null;
    }
}