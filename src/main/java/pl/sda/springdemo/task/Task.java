package pl.sda.springdemo.task;

import pl.sda.springdemo.progres.Progress;
import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.users.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;



    private int week;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int year;

    private Integer storyPoints;

    private Integer weight;

    private Progress progress;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    public Task() {
    }


    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Task(String name, String description, int week,int year, Integer storyPoints, Integer weight, User user, Progress progress, Project project) {
        this.name = name;
        this.description = description;
        this.week=week;
        this.year=year;
        this.storyPoints = storyPoints;
        this.weight = weight;
        this.progress = progress;
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
                ", week=" + week +
                ", weight=" + weight +
                ", progress=" + progress +
                ", user=" + user +
                '}';
    }
}
