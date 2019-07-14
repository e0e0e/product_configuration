package pl.sda.springdemo.users;

import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String description;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public Project() {
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

//    public User getUser() {
//        return user;
//    }
}
