package pl.sda.springdemo.task;

import org.springframework.stereotype.Service;
import pl.sda.springdemo.Wall;
import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.projects.ProjectRepository;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.sprint.SprintRepositoryJPA;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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


    public boolean create(String name, String description, long sprintId,
                          Integer storyPoints, Integer weight, User user, Project project) {


        Sprint sprint = sprintRepositoryJPA.findById(sprintId).get();

        // sprintRepositoryJPA.save(sprint);
        Task task = new Task(name, description, sprint, storyPoints, weight, user, Progress.TO_DO, project);
        sprint.getTasks().add(task);

        user.getTasks().add(task);

        Task created = taskRepository.save(task);
        project.getTask().add(task);
        Project createdProject = projectRepository.save(project);
//        User createUser = userRepository.save(user);!!!!!!!!


        return created.getId() != null;
    }

    public static LocalDate LocalDateFromWeekYearAndWeek(int weekYear,
                                                         int weekOfWeekYear,
                                                         int day) {
        Calendar cal = Calendar.getInstance();
        cal.setWeekDate(weekYear, weekOfWeekYear, day);
        LocalDate localDate = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

        return localDate;
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

    public Long getPresentSprint() {

        Long result = taskRepository.getPresentSprint();

        if (result == null) {
            result = taskRepository.getNearestSprint();

        }
        return result;
    }

    public List<Task> findAllFromSprint(Long sprintId) {
        return taskRepository.findAllFromSprint(sprintId);
    }


}
