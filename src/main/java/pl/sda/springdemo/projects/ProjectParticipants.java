package pl.sda.springdemo.projects;

import pl.sda.springdemo.users.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProjectParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "user")
    private Set<Project> projects;
    @ManyToOne
    private User user;

    public ProjectParticipants(Set<Project> projects, User user) {
        this.projects = projects;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
