package pl.sda.springdemo.projects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.springdemo.task.Task;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    List<Project> findAll();

    @Query(value = "SELECT * FROM project WHERE user_id like (Select id from user where username like ?1)", nativeQuery = true)
    List<Project> finaAllWhereAdmin(String loggedUserName);


    @Query(value = "Select * from project inner join participants ON project.id=participants.project_id  WHERE participants.user_id like (Select id from user where username like ?1) group by project_name", nativeQuery = true)
    List<Project> findAllWhereParticipate(String loggedUserName);


}
