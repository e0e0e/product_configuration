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


        // model.addAttribute("loggedUser", userService.getLogged());
        User user = userService.findUserByname(username);

        try {

            //dodanie usera
            boolean result = projectService.create(projectName, description, user);


            model.addAttribute("createProjectResult", result);
            List<Project> projects = projectService.findAll();


//            userService.findById(user).setProjectsParticipants(projects);

            model.addAttribute("projects", projects);

            //System.out.println(email + " " + password + " " + datfB);
//            model.addAttribute("title", "Project List");
//            model.addAttribute("path", "project/projectList");
//            return "main";
            return "redirect:users/projectList";
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
    public String listProjects(HttpServletRequest request,
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
//        model.addAttribute("loggedUser", userService.getLogged());

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
//        model.addAttribute("loggedUser", userService.getLogged());


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
//        userService.logout();

        return "user/login";
    }

    @GetMapping("/project/show")
    public String showProject(@RequestParam Long projectId,
                              Model model) {

        Project project = projectService.findById(projectId).get();

//       // Set<Task> taskSet = project.getTask();
        List<Task> taskList = project.getTask().stream()
                .sorted(Comparator.comparing(e -> e.getSprint().getStartDate()))
                .collect(Collectors.toList());
//       new ArrayList(new TreeSet(project.getTask()));
//
//        List<Integer> numberOfDays = project.getTask().stream().map(e ->
//                Math.abs(Period.between(e.getSprint().getStartDate(), e.getSprint().getFinishDate()).getDays()))
//                .collect(Collectors.toList());


        if (taskList.size() > 0) {


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
//                        .collect(Collectors.toMap(x -> x.getSprintId(), x -> x));

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

//    @GetMapping("project/taskWall")
//    private String showProjectWall(Model model){
//
//        List<Task> taskList=taskService.findAll();
//        List<Task> tasksToDo=taskService.findToDo();
//        List<Task> tasksInProgress=taskService.findInProgress();
//        List<Task> tasksDone=taskService.findDone();
//
//
//        model.addAttribute("tasksToDo",tasksToDo);
//        model.addAttribute("tasksInProgress",tasksInProgress);
//        model.addAttribute("tasksDone",tasksDone);
//        model.addAttribute("tasks", taskList);
//
//        model.addAttribute("title", "Wall");
//        model.addAttribute("path", "project/taskWall");
//        return "main";
//    }

}