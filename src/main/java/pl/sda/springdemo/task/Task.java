package pl.sda.springdemo.task;

import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.users.User;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @OneToOne
    private Sprint sprint;

    private Integer weight;

    private Progress progress;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    public Task() {
    }



    public Task(String name, String description, Sprint sprint, Integer weight, User user, Project project) {
        this.name = name;
        this.description = description;
        this.sprint = sprint;
        this.weight = weight;

        this.user = user;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
