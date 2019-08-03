package pl.sda.springdemo.projects;

import org.springframework.stereotype.Service;
import pl.sda.springdemo.users.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public boolean create(String projectName, String description, User user) {

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
}
