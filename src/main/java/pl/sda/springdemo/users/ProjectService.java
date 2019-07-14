package pl.sda.springdemo.users;

import org.springframework.stereotype.Service;

import java.util.List;

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

       Project created= projectRepository.save(new Project(projectName,description,user));
        return created.getId() != null;
    }

    public void delete(long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not Found"));

        projectRepository.delete(project);
    }
}
