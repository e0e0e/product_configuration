package pl.sda.springdemo.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.springdemo.sprint.SprintService;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {
    private final UserService userService;
    private final SprintService sprintService;
    private final TaskService taskService;

    public TaskController(UserService userService, SprintService sprintService, TaskService taskService) {
        this.userService = userService;
        this.sprintService = sprintService;

        this.taskService = taskService;
    }
//    private final TaskService taskService;


    @GetMapping("/tasks")
    public String taskForm(Model model) {


        model.addAttribute("users", userService.findAll());
        model.addAttribute("sprints", sprintService.findAllSprints());
        model.addAttribute("title", "Task form");
        model.addAttribute("path", "task/task");
        return "main";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam String from,
                          @RequestParam String to,
                          @RequestParam Integer storyPoints,
                          @RequestParam Integer weight,
                          @RequestParam Long userId,
                          Model model) {
        System.out.println("Task name: "+name);

       boolean tastCreated= taskService.create(name, description,
                LocalDate.parse(from),
                LocalDate.parse(to),
                storyPoints, weight,
                userService.findById(userId));

        model.addAttribute("createUserResult", tastCreated);
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);

//        model.addAttribute("sprints", sprintService.findAllSprints());

        model.addAttribute("title", "Add Task");
        model.addAttribute("path", "task/taskList");
        return "main";
    }


}
