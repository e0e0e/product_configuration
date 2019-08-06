package pl.sda.springdemo.task;

import org.springframework.stereotype.Service;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.projects.ProjectRepository;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.sprint.SprintRepositoryJPA;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepositoryJPA sprintRepositoryJPA;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, SprintRepositoryJPA sprintRepositoryJPA, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepositoryJPA = sprintRepositoryJPA;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    public boolean create(String name, String description, LocalDate startDate, LocalDate finishDate,
                          Integer storyPoints, Integer weight, User user, Project project) {
        Sprint sprint = new Sprint(startDate, finishDate, storyPoints);

        sprintRepositoryJPA.save(sprint);
        Task task = new Task(name, description, sprint, weight, user, project);
        sprint.setTask(task);
        //
        user.getTasks().add(task);

        Task created = taskRepository.save(task);
        project.getTask().add(task);
        Project createdProject = projectRepository.save(project);
        User createUser = userRepository.save(user);


        return created.getId() != null;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
