package pl.sda.springdemo.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.springdemo.projects.Project;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

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

    @Query(value = "Select top 1 * from sprint WHERE finish_date<CURRENT_DATE() order by datediff(day , CURRENT_DATE(), finish_date)", nativeQuery = true)
    Long getNearestSprint();
}
