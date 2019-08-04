package pl.sda.springdemo.task;

import org.springframework.stereotype.Service;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.sprint.SprintRepositoryJPA;
import pl.sda.springdemo.users.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepositoryJPA sprintRepositoryJPA;

    public TaskService(TaskRepository taskRepository, SprintRepositoryJPA sprintRepositoryJPA) {
        this.taskRepository = taskRepository;
        this.sprintRepositoryJPA = sprintRepositoryJPA;
    }


    public boolean create(String name, String description, LocalDate startDate, LocalDate finishDate,
                          Integer storyPoints, Integer weight, User user) {
        Sprint sprint = new Sprint(startDate, finishDate, storyPoints);

        sprintRepositoryJPA.save(sprint);
        Task task = new Task(name, description, sprint, weight, user);
        sprint.setTask(task);
        //
        user.getTasks().add(task);
        Task created = taskRepository.save(task);

        return created.getId() != null;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
