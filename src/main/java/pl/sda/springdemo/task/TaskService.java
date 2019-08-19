package pl.sda.springdemo.task;

import org.springframework.stereotype.Service;
import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.projects.ProjectRepository;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    public boolean create(String name, String description, int week,int year,
                          Integer storyPoints, Integer weight, User user, Project project) {



        Task task = new Task(name, description, week,year, storyPoints, weight, user, Progress.TO_DO, project);


        user.getTasks().add(task);

        Task created = taskRepository.save(task);
        project.getTask().add(task);
        Project createdProject = projectRepository.save(project);
//        User createUser = userRepository.save(user);!!!!!!!!


        return created.getId() != null;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    public void changeProgress(Long taskId, String progress) {
        Task task = taskRepository.findById(taskId).get();
        for (Progress p : Progress.values()) {
            if (p.toString().equals(progress)) {
                task.setProgress(p);
                taskRepository.save(task);
            }
        }


    }

    public void DeleteTask(long taskId) {

        taskRepository.deleteById(taskId);
    }

    public List<Task> findToDo() {

        return taskRepository.findToDO();
    }

    public List<Task> findInProgress() {
        return taskRepository.findInProgress();
    }

    public List<Task> findDone() {
        return taskRepository.findDone();
    }

    public List<Task> findTasksToDo(Long projectId, int status) {

        return taskRepository.findTasksToDo(projectId, status);
    }


    public List<Task> findAllInWeek(int weekNumber) {
        return taskRepository.findAllInWeek(weekNumber);
    }


    public List<Task> findAllBeforeWeek(int weekNumber) {
        return taskRepository.findAllBeforeWeek(weekNumber);
    }
}
