package pl.sda.springdemo.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer planedStoryPoints;

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
