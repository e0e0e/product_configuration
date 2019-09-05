package pl.sda.pms.projects;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    List<Project> findAll();

    @Query(value = "SELECT * FROM project WHERE user_id like (Select id from user where username like ?1)", nativeQuery = true)
    List<Project> finaAllWhereAdmin(String loggedUserName);

    @Query(value = "SELECT * FROM project WHERE project_name like ?1)", nativeQuery = true)
    List<Project> findIfProjectNameExists(String projectName);

    @Query(value = "Select * from project inner join participants ON project.id=participants.project_id  WHERE participants.user_id like (Select id from user where username like ?1) group by project_name", nativeQuery = true)
    List<Project> findAllWhereParticipate(String loggedUserName);


    @Modifying
    @Transactional
    @Query(value = "UPDATE project set project_name=?2,description=?3 where id like ?1", nativeQuery = true)
    void updateProject(long projectId, String projectName, String description);


}
