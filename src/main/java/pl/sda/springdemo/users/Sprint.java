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

    public Sprint(LocalDate startDate, LocalDate finidhDate, Integer planedStoryPoints) {
        this.startDate = startDate;
        this.finishDate = finidhDate;
        this.planedStoryPoints = planedStoryPoints;
    }

    public Long getId() {
        return id;
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
