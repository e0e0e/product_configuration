package pl.sda.springdemo.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.springdemo.Wall;
import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.projects.ProjectService;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.sprint.SprintService;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

@Controller
public class TaskController {
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final SprintService sprintService;

    public TaskController(UserService userService, TaskService taskService, ProjectService projectService, SprintService sprintService) {
        this.userService = userService;


        this.taskService = taskService;
        this.projectService = projectService;
        this.sprintService = sprintService;
    }
//    private final TaskService taskService;


    @GetMapping("/tasks")
    public String taskForm(@RequestParam(required = false) Long projectId,
                           HttpServletRequest request,
                           Model model) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY")
//        List<Sprint> sprintList=sprintService.findAllSprints();
//        sprintList.get(0).getStartDate().format(formatter.format())
        String loggedUserName = request.getRemoteUser();
        model.addAttribute("project", projectService.findById(projectId).get());
        model.addAttribute("sprints", sprintService.findAllSprints());
        model.addAttribute("users", userService.findAllWithException(loggedUserName));
        model.addAttribute("title", "Task form");
        model.addAttribute("path", "task/task");
        return "main";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam long sprintId,
                          @RequestParam Integer storyPoints,
                          @RequestParam Integer weight,
                          @RequestParam Long userId,
                          @RequestParam Long projectId,
                          Model model) {


        User user = userService.findById(userId);
        Project project = projectService.findById(projectId).get();


        boolean tastCreated = taskService.create(name, description,
                sprintId,
                storyPoints, weight,
                user,
                project
        );


        user.getProjectsParticipants().add(project);
        project.getUsers().add(user);
        userService.save(user);


        model.addAttribute("createUserResult", tastCreated);

        return "redirect:/project/show?projectId=" + projectId;
    }

    @GetMapping("/taskList")
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Add Task");
        model.addAttribute("path", "task/taskList");
        return "main";

    }

    @GetMapping("/task/progressChange")
    public String changeProgress(@RequestParam Long taskId,
                                 Model model) {

        Task task = taskService.findById(taskId);
        model.addAttribute("progress", Progress.values());
        model.addAttribute("task", task);
        model.addAttribute("title", "Change Progress");
        model.addAttribute("path", "task/changeProgress");
        return "main";
    }

    @PostMapping("/task/progressChange")
    private String changeProgress(@RequestParam Long taskId,
                                  @RequestParam String progress,
                                  Model model) {

        taskService.changeProgress(taskId, progress);

        return "redirect:/project/show?projectId=" + taskService.findById(taskId).getProject().getId();
    }

    @GetMapping("/task/progressToNextChange")
    private String changeToNextProgress(@RequestParam Long taskId,
                                        @RequestParam String progress,
                                        @RequestParam(required = false) Integer backToWall,
                                        Model model) {

        taskService.changeProgress(taskId, progress);

        if (backToWall != null) {
            return "redirect:/taskWall?sprintId=" + backToWall;

        } else {
            return "redirect:/project/show?projectId=" + taskService.findById(taskId).getProject().getId();
        }
    }


    @GetMapping("/task/delete")
    public String deleteTask(@RequestParam long taskId,
                             @RequestParam long projectId) {

        taskService.DeleteTask(taskId);

        return "redirect:/project/show?projectId=" + projectId;

    }
    @GetMapping("/taskWallNext")
    public String nextSprint(@RequestParam long sprintId){

        long id=sprintService.findNextSprint(sprintId);

        if(id>=0){
            return "redirect:/taskWall?sprintId=" + id;
        }

        return "redirect:/taskWall?sprintId=" + sprintId;
    }

    @GetMapping("/taskWallPrevious")
    public String previousSprint(@RequestParam long sprintId){

        long id=sprintService.findPreviousSprint(sprintId);

        if(id>=0){
            return "redirect:/taskWall?sprintId=" + id;
        }

        return "redirect:/taskWall?sprintId=" + sprintId;
    }


    @GetMapping("/taskWall")
    private String showWall(@RequestParam(required = false) Long sprintId,
                            Model model) {

        if (sprintId == null) {
            sprintId = taskService.getPresentSprint();
        }


//        List<Task> taskList = taskService.findAllFromSprint(sprintId);
        List<Task> taskList = taskService.findAllFromSprintAndBeforeNotFinished(sprintId);

        List<Task> tasksInWeek = taskList;


        Map<Project, List<Task>> projectsInWeek = new HashMap<>();
        for (Task task : tasksInWeek) {
            if (projectsInWeek.containsKey(task.getProject())) {
                projectsInWeek.get(task.getProject()).add(task);
            } else {
                List<Task> listForProject = new ArrayList<>();
                listForProject.add(task);
                projectsInWeek.put(task.getProject(), listForProject);

            }
        }
        TreeMap<Project, List<Task>> projectsInWeekSorted = new TreeMap<>(projectsInWeek);


        model.addAttribute("projectsInWeek", projectsInWeekSorted);//
        model.addAttribute("sprints", sprintService.findAllSprints() );//

        model.addAttribute("tasks", taskList);
        model.addAttribute("now", LocalDate.now());

        model.addAttribute("title", "Wall");
        model.addAttribute("path", "task/taskWall");
        return "main";
    }

    @GetMapping("/task/show")
    public String ShowTask(@RequestParam long taskId,
                           Model model) {

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("title", "Task");
        model.addAttribute("path", "task/show");

        return "main";
    }

}
