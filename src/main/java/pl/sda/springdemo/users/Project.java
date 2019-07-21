package pl.sda.springdemo.users;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String description;
    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> users;

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


    public String showUsersInProject () {
        String str = "";
        try {
            str= users.stream()
                    .sorted(Comparator.comparing(o -> o.getUserName()))
                    .map(e->e.getUserName())
                    .collect(Collectors.joining(", "));

//                                .forEach(e-> str.concat(e.getProjectName() + "<br/>"));

            // }
        }catch(NullPointerException e){
            System.out.println("Concat String of users is:"+ e.getMessage());
        }
        return str;
    }


}
