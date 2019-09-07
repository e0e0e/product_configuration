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

    @Query(value = "SELECT * FROM project WHERE user_id like ?1", nativeQuery = true)
    List<Project> finaAllWhereAdmin(Long userId);

    @Query(value = "SELECT count(ID) FROM project WHERE PROJECT_NAME=?1", nativeQuery = true)
    int findIfProjectNameExists(String projectName);

    @Query(value = "Select * from project inner join participants ON project.id=participants.project_id  WHERE participants.user_id like ?1 group by project_name", nativeQuery = true)
    List<Project> findAllWhereParticipate(Long userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE project set project_name=?2,description=?3 where id like ?1", nativeQuery = true)
    void updateProject(long projectId, String projectName, String description);

    @Query(value = "SELECT count(ID) FROM project WHERE PROJECT_NAME=?2 and id !=?1", nativeQuery = true)
    int findIfProjectNameExistsExceptThis(long projectId, String projectName);
}
