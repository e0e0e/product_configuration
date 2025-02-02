package pl.sda.pms.users;

import org.hibernate.envers.Audited;
import pl.sda.pms.projects.Project;
import pl.sda.pms.task.Task;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Project> projects;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @ManyToMany
    @JoinTable(name = "PARTICIPANTS", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "project_id") })
    private Set<Project> projectsParticipants;

    private String avatar = "default.png";
    private String authorities;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public User() {
    }

    public void setProjectsParticipants(Set<Project> projectsParticipants) {
        this.projectsParticipants = projectsParticipants;
    }

    public User(String login, String password, String email, String username, Set<Project> projects) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.username = username;
        this.projects = projects;
    }

    public Set<Project> getProjectsParticipants() {
        return projectsParticipants;
    }

    public User(String login, String password, String email, String username) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(email, user.email)
                && Objects.equals(username, user.username) && Objects.equals(projects, user.projects)
                && Objects.equals(projectsParticipants, user.projectsParticipants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, username, projects, projectsParticipants);
    }

    @Override
    public String toString() {

        String str = "";
        try {

            str = projects.stream().sorted(Comparator.comparing(o -> o.getProjectName())).map(e -> e.getProjectName())
                    .collect(Collectors.joining(", "));

        } catch (NullPointerException e) {
            System.out.println("Concat String of projects is:" + e.getMessage());
        }
        return str;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
