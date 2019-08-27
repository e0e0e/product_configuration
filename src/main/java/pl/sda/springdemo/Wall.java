package pl.sda.springdemo;

import pl.sda.springdemo.projects.Project;
import pl.sda.springdemo.sprint.Sprint;
import pl.sda.springdemo.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public class Wall {


     private TreeMap<Project, List<Task>> projectsInWeek;
     private List<Sprint> sprints;
     private Sprint theSprint;
     private LocalDate now;

    public Wall(TreeMap<Project, List<Task>> projectsInWeek, List<Sprint> sprints, Sprint theSprint) {
        this.projectsInWeek = projectsInWeek;
        this.sprints = sprints;
        this.theSprint = theSprint;
        this.now = LocalDate.now();
    }

    public LocalDate getNow() {
        return now;
    }

    public TreeMap<Project, List<Task>> getprojectsInWeek() {
        return projectsInWeek;
    }

    public void setprojectsInWeek(TreeMap<Project, List<Task>> projectsInWeekSorted) {
        this.projectsInWeek = projectsInWeekSorted;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Sprint getTheSprint() {
        return theSprint;
    }

    public void setTheSprint(Sprint theSprint) {
        this.theSprint = theSprint;
    }
}
