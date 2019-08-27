package pl.sda.pms.sprint;

import pl.sda.pms.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer planedStoryPoints;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.REMOVE)
    private Set<Task> tasks;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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
