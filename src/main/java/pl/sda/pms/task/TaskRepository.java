package pl.sda.pms.task;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.pms.projects.Project;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();

    @Query(value = "SELECT * FROM task WHERE progress like 0", nativeQuery = true)
    List<Task> findToDO();

    @Query(value = "SELECT * FROM task WHERE progress like 1", nativeQuery = true)
    List<Task> findInProgress();

    @Query(value = "SELECT * FROM task WHERE progress like 2", nativeQuery = true)
    List<Task> findDone();

    @Query(value = "SELECT * FROM task WHERE progress like ?2 and project_id like ?1", nativeQuery = true)
    List<Task> findTasksToDo(Long projectId, int status);

    @Query(value = "Select * from task\n" +
            "             inner join project ON project.id=task.project_id\n" +
            "            inner join sprint ON task.sprint_id=sprint.id\n" +
            "            WHERE (WEEK(sprint.start_date)+1)=?1 order by project.id", nativeQuery = true)
    List<Task> findAllInWeek(int weekNumber);

    @Query(value = "Select * from task\n" +
            "             inner join project ON project.id=task.project_id\n" +
            "            inner join sprint ON task.sprint_id=sprint.id\n" +
            "            WHERE (WEEK(sprint.start_date)+1)<=?1 order by project.id", nativeQuery = true)
    List<Task> findAllBeforeWeek(int weekNumber);


    @Query(value = "Select id from sprint WHERE start_date<=CURRENT_DATE() and finish_date>=CURRENT_DATE()", nativeQuery = true)
    Long getPresentSprint();

    @Query(value = "Select * from task WHERE sprint_id like ?1", nativeQuery = true)
    List<Task> findAllFromSprint(Long sprintId);

    @Query(value = "Select top 1 * from sprint WHERE finish_date<CURRENT_DATE() order by datediff(day , CURRENT_DATE(), finish_date) desc", nativeQuery = true)
    Long getNearestSprint();

    @Query(value = "Select * from task inner join sprint on sprint.ID=task.SPRINT_ID WHERE SPRINT_ID like ?1 order by task.PROGRESS", nativeQuery = true)
    List<Task> findBySprintId(Long sprintId);


    @Query(value = "Select * from task inner join sprint on sprint.ID=task.SPRINT_ID WHERE sprint.START_DATE<(Select sprint.START_DATE from sprint where id=?1) and task.progress<2 order by task.PROGRESS", nativeQuery = true)
    List<Task> findReminingTasks(Long sprintId);

    @Query(value = "Select * from task inner join sprint on sprint.ID=task.SPRINT_ID WHERE SPRINT_ID like ?1 and task.PROGRESS like ?2", nativeQuery = true)
    List<Task> findBySprintTaskToDo(Long sprintId, int progress);

    @Query(value = "Select * from project inner join task on PROJECT.ID=task.project_id\n" +
            "    inner join sprint on sprint.id=task.sprint_id WHERE task.SPRINT_ID like ?1", nativeQuery = true)
    List<Project> projectsInSprint(Long sprintId);

    @Query(value = "Select * from task inner join sprint on sprint.ID=task.SPRINT_ID  WHERE sprint_id like ?1 or (sprint.FINISH_DATE<(Select sprint.FINISH_DATE from sprint where SPRINT.id=?1) and task.PROGRESS<2)", nativeQuery = true)
    List<Task> findAllFromSprintAndBeforeNotFinished(Long sprintId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task set task.NAME=?1,description=?2,sprint_id=?3, STORY_POINTS=?4, weight=?5, user_id=?6 where id like ?7", nativeQuery = true)
    void updateTask(String name, String description, long sprintId, Integer storyPoints, Integer weight, Long userId, long taskId);

    @Query(value = "Select count(id) from task WHERE NAME like ?2 and id not like ?1 and PROJECT_ID like ?3", nativeQuery = true)
    int findIfTaskNameExistsInProjectExceptThis(long taskId, String name, long projectId);
}
