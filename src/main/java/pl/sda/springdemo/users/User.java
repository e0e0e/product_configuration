package pl.sda.springdemo.users;

import javax.persistence.*;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String password;
    private String email;
    private String userName;

    @OneToMany(mappedBy = "user")
    private Set<Project> projects;


    public User() {
    }

    public User(String login, String password, String email, String userName) {
        this.login = login;

        this.password = password;
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public String getUserName() {
        return userName;
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
}
