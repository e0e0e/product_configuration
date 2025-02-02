package pl.sda.pms.task;

import pl.sda.pms.progres.Progress;
import pl.sda.pms.projects.Project;
import pl.sda.pms.sprint.Sprint;
import pl.sda.pms.users.User;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    @ManyToOne()
    private Sprint sprint;

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    private Integer storyPoints;

    private Integer weight;

    private Progress progress;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

	

    public Task() {
    }



    public Task(String name, String description, Sprint sprint, Integer storyPoints, Integer weight, User user,Progress progress, Project project) {
        this.name = name;
        this.description = description;
        this.sprint = sprint;
        this.storyPoints=storyPoints;
        this.weight = weight;
        this.progress=progress;
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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sprint=" + sprint +
                ", weight=" + weight +
                ", progress=" + progress +
                ", user=" + user +
                '}';
    }
}
