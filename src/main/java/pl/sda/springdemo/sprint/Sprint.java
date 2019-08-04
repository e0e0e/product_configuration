package pl.sda.springdemo.sprint;

import pl.sda.springdemo.task.Task;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer planedStoryPoints;

    @OneToOne
    private Task task;

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Sprint(){

    }
    public Sprint(LocalDate startDate, LocalDate finishDate, Integer planedStoryPoints) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.planedStoryPoints = planedStoryPoints;
    }

    public Long getId() {
        return id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setPlanedStoryPoints(Integer planedStoryPoints) {
        this.planedStoryPoints = planedStoryPoints;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public Integer getPlanedStoryPoints() {
        return planedStoryPoints;
    }
}
