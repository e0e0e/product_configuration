package pl.sda.pms.task;

import org.springframework.stereotype.Service;
import pl.sda.pms.Wall;
import pl.sda.pms.progres.Progress;
import pl.sda.pms.projects.Project;
import pl.sda.pms.projects.ProjectRepository;
import pl.sda.pms.sprint.Sprint;
import pl.sda.pms.sprint.SprintRepositoryJPA;
import pl.sda.pms.users.User;
import pl.sda.pms.users.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepositoryJPA sprintRepositoryJPA;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final SmallTaskRepository smallTaskRepository;

    public TaskService(TaskRepository taskRepository, SprintRepositoryJPA sprintRepositoryJPA,
            ProjectRepository projectRepository, UserRepository userRepository,
            SmallTaskRepository smallTaskRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepositoryJPA = sprintRepositoryJPA;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.smallTaskRepository = smallTaskRepository;
    }

    public static LocalDate LocalDateFromWeekYearAndWeek(int weekYear,
                                                         int weekOfWeekYear,
                                                         int day) {
        Calendar cal = Calendar.getInstance();
        cal.setWeekDate(weekYear, weekOfWeekYear, day);
        LocalDate localDate = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

        return localDate;
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

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    public void changeProgress(Long taskId, String progress, boolean next) {


        Task task = taskRepository.findById(taskId).get();
        for (Progress p : Progress.values()) {
            if (p.toString().equals(progress)) {
                if(next) {
                    task.setProgress(p.getNext());
                }else{
                    task.setProgress(p.getPrevious());
                }

                if (task.getProgress()!=null){

                    taskRepository.save(task);
                }
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


//    public List<Task> findAllFromSprintAndBeforeNotFinished(Long sprintId) {
//
//        return taskRepository.findAllFromSprintAndBeforeNotFinished(sprintId);
//    }

    public Wall prepareTaskWall(Long sprintId, String filter) {
        if (sprintId == null) {
            sprintId = getPresentSprint();
        }

        List<Task> taskList=new ArrayList<>();
        switch (filter) {
            case "unfinished":
                taskList = taskRepository.findAllFromSprintAndBeforeNotFinished(sprintId);
                break;
            case "current":
                taskList = findAllFromSprint(sprintId);
                break;
            default:
                taskList = findAllFromSprint(sprintId);
        }


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

        return new Wall(projectsInWeekSorted, sprintRepositoryJPA.findAllSprintsSorted(), sprintRepositoryJPA.findById(sprintId).get());
    }

    public void updateTask(long taskId, String name, String description,
                           long sprintId, Integer storyPoints, Integer weight,
                           Long userId) {

        if (taskRepository.findIfTaskNameExistsInProjectExceptThis(taskId, name,taskRepository.findById(taskId).get().getProject().getId()) > 0) {//if not same projectId
            throw new RuntimeException("Task name already in use in this project");
        }
        taskRepository.updateTask(name,description,sprintId,storyPoints,weight,userId, taskId);
    }

	public boolean createSmallTask(Integer i) {
        SmallTask smallTask=new SmallTask();
        SmallTask created=smallTaskRepository.save(smallTask);
        return created.getId() != null;
	}
}

