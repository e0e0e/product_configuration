package pl.sda.springdemo.users;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    @ManyToMany
    @JoinTable(
            name = "PARTICIPANTS",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projectsParticipants;


    public User() {
    }


    public void setProjectsParticipants(Set<Project> projectsParticipants) {
        this.projectsParticipants = projectsParticipants;
    }

    public User(String login, String password, String email, String userName, Set<Project> projects) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userName = userName;
        this.projects = projects;
    }

    public Set<Project> getProjectsParticipants() {
        return projectsParticipants;
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

    @Override
    public String toString() {

        String str = "";
        try {

           // for (Project project : projects) {
//                str = str.concat(project.getProjectName() + "<br/>");
               str= projects.stream()
                       .sorted(Comparator.comparing(o -> o.getProjectName()))
                       .map(e->e.getProjectName())
                        .collect(Collectors.joining(", "));

//                                .forEach(e-> str.concat(e.getProjectName() + "<br/>"));

           // }
        }catch(NullPointerException e){
            System.out.println("Concat String of projects is:"+ e.getMessage());
        }
        return str;
    }
}
