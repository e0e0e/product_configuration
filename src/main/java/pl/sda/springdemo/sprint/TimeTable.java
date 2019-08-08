package pl.sda.springdemo.sprint;

import pl.sda.springdemo.task.Task;

import java.time.LocalDate;
import java.util.List;

public class TimeTable {

    private LocalDate startDate;
    private Integer daysToStart;
    private Integer daysToFinish;
    private Integer numberOfDays;
    private Long sprintId;
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TimeTable(LocalDate startDate, Integer daysToStart, Integer daysToFinish, Integer numberOfDays, Long sprintId, Task task) {
        this.startDate = startDate;
        this.daysToStart = daysToStart;
        this.daysToFinish = daysToFinish;
        this.numberOfDays = numberOfDays;
        this.sprintId = sprintId;
        this.task=task;
    }




    @Override
    public String toString() {
        return "TimeTable{" +
                "startDate=" + startDate +
                ", daysToStart=" + daysToStart +
                ", daysToFinish=" + daysToFinish +
                ", numberOfDays=" + numberOfDays +
                ", sprintId=" + sprintId +
                '}';
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDaysToStart() {
        return daysToStart;
    }

    public void setDaysToStart(Integer daysToStart) {
        this.daysToStart = daysToStart;
    }

    public Integer getDaysToFinish() {
        return daysToFinish;
    }

    public void setDaysToFinish(Integer daysToFinish) {
        this.daysToFinish = daysToFinish;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
