package pl.sda.pms.projects;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.stereotype.Service;
import pl.sda.pms.users.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProjectService {

    @PersistenceContext
    EntityManager entityManager;

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public boolean create(String projectName, String description, User user) {

        if (projectRepository.findIfProjectNameExists(projectName) > 0) {
            throw new RuntimeException("Project name already in use");
        }

        Project created = projectRepository.save(new Project(projectName, description, user));

        return created.getId() != null;
    }

    public void delete(long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not Found"));

        projectRepository.delete(project);
    }

    public Optional<Project> findById(long projectId) {
        return projectRepository.findById(projectId);
    }

    public Set<User> getUsers(long projectId) {
        return projectRepository.findById(projectId).get().getUsers();
    }

    public List<Project> findAllWhereAdmin(Long userId) {

        return projectRepository.finaAllWhereAdmin(userId);
    }

    public List<Project> findAllWhereParticipate(Long userId) {
        return projectRepository.findAllWhereParticipate(userId);
    }

    public void updateProject(long projectId, String projectName, String description) {
        if (projectRepository.findIfProjectNameExistsExceptThis(projectId, projectName) > 0) {// if not same projectId
            throw new RuntimeException("Project name already in use");
        }
        Project project = projectRepository.findById(projectId).get();
        project.setDescription(description);
        project.setProjectName(projectName);
        projectRepository.updateProject(projectId, projectName, description);

    }

    public List<Project> findAllById(Long projectId) {
        
        List<Project> projects = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(Project.class, true, true)
                .add(AuditEntity.id().eq(projectId))
                .getResultList();
       
       
                return projects;
    }
}
