package pl.sda.pms.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.pms.progres.Progress;
import pl.sda.pms.projects.Project;
import pl.sda.pms.projects.ProjectService;

import pl.sda.pms.sprint.SprintService;
import pl.sda.pms.users.User;
import pl.sda.pms.users.UserService;

import javax.servlet.http.HttpServletRequest;
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


    @GetMapping("/tasks")
    public String taskForm(@RequestParam(required = false) Long projectId,
                           HttpServletRequest request,
                           Model model) {

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
                                        @RequestParam(required = false) String previous,
                                        Model model) {

        taskService.changeProgress(taskId, progress);

        if (backToWall != null) {
            return "redirect:/taskWall?sprintId=" + backToWall+"&previous="+previous;

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
    public String nextSprint(@RequestParam long sprintId,
                             @RequestParam(required = false) String previous) {

//        if(previous==null){
//
//        }

        long id = sprintService.findNextSprint(sprintId);

        if (id >= 0) {
            return "redirect:/taskWall?sprintId=" + id+"&previous="+previous;
        }

        return "redirect:/taskWall?sprintId=" + sprintId+"&previous="+previous;
    }

    @GetMapping("/taskWallPrevious")
    public String previousSprint(@RequestParam long sprintId,
                                 @RequestParam(required = false) String previous) {

        long id = sprintService.findPreviousSprint(sprintId);

        if (id >= 0) {
            return "redirect:/taskWall?sprintId=" + id+"&previous="+previous;
        }

        return "redirect:/taskWall?sprintId=" + sprintId+"&previous="+previous;
    }


    @GetMapping("/taskWall")
    private String showWall(@RequestParam(required = false) Long sprintId,
                            @RequestParam(required = false) String previous,
                            Model model) {

        if (previous == null) {
            previous="unfinished";
//            model.addAttribute("previous","unfinished");
           // model.addAttribute("wall", taskService.prepareTaskWall(sprintId, "unfinished"));

        }

            model.addAttribute("wall", taskService.prepareTaskWall(sprintId, previous));


        model.addAttribute("title", "Wall");
        model.addAttribute("path", "task/taskWall");
        return "main";
    }

    @GetMapping("/task/show")
    public String ShowTask(@RequestParam long taskId,
                           Model model) {

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("title", "Task");
        model.addAttribute("path", "task/showTask");

        return "main";
    }

}
