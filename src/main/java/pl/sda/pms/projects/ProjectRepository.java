package pl.sda.pms.projects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    List<Project> findAll();

    @Query(value = "SELECT * FROM project WHERE user_id like (Select id from user where username like ?1)", nativeQuery = true)
    List<Project> finaAllWhereAdmin(String loggedUserName);


    @Query(value = "Select * from project inner join participants ON project.id=participants.project_id  WHERE participants.user_id like (Select id from user where username like ?1) group by project_name", nativeQuery = true)
    List<Project> findAllWhereParticipate(String loggedUserName);

//    @Query(value = "Select * from task inner join project ON project.id=task.project_id " +
//            "inner join sprint ON task.sprint_id=sprint.id " +
//            "WHERE WEEK(sprint.start_date)=?1", nativeQuery = true)

}
