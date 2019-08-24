package pl.sda.springdemo;

import pl.sda.springdemo.task.Task;

import java.util.List;

public class Wall {


    public List<Task> tasksToDo;
    private List<Task> tasksInProgress;
    private List<Task> tasksDone;
    private List<Task> remainingTasksToDo;
    private List<Task> remainingTasksInProgress;
    private List<Task> remainingTasksDone;


    public Wall() {
    }

    public List<Task> getRemainingTasksToDo() {
        return remainingTasksToDo;
    }

    public void setRemainingTasksToDo(List<Task> remainingTasksToDo) {
        this.remainingTasksToDo = remainingTasksToDo;
    }

    public List<Task> getRemainingTasksInProgress() {
        return remainingTasksInProgress;
    }

    public void setRemainingTasksInProgress(List<Task> remainingTasksInProgress) {
        this.remainingTasksInProgress = remainingTasksInProgress;
    }

    public List<Task> getRemainingTasksDone() {
        return remainingTasksDone;
    }

    public void setRemainingTasksDone(List<Task> remainingTasksDone) {
        this.remainingTasksDone = remainingTasksDone;
    }




    public List<Task> getTasksToDo() {
        return tasksToDo;
    }

    public void setTasksToDo(List<Task> tasksToDo) {
        this.tasksToDo = tasksToDo;
    }

    public List<Task> getTasksInProgress() {
        return tasksInProgress;
    }

    public void setTasksInProgress(List<Task> tasksInProgress) {
        this.tasksInProgress = tasksInProgress;
    }

    public List<Task> getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(List<Task> tasksDone) {
        this.tasksDone = tasksDone;
    }
}
