package pl.sda.pms.projects;

import pl.sda.pms.task.Task;
import pl.sda.pms.users.User;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Audited
public class Project implements Comparable<Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String projectName;
    private String description;
    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> users;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private Set<Task> task;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public Project() {
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Project(String projectName, String description, User user) {
        this.projectName = projectName;
        this.description = description;
        this.user = user;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }


    public String showUsersInProject() {
        String str = "";
        try {
            str = users.stream()
                    .sorted(Comparator.comparing(o -> o.getUsername()))
                    .map(e -> e.getUsername())
                    .collect(Collectors.joining(", "));

//                                .forEach(e-> str.concat(e.getProjectName() + "<br/>"));

            // }
        } catch (NullPointerException e) {
            System.out.println("Concat String of users is:" + e.getMessage());
        }
        return str;
    }


    @Override
    public int compareTo(Project project) {
        return (int)(this.id - project.getId());
    }
}
