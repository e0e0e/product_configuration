package pl.sda.springdemo.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long sprintId;
    private Integer weight;
    private Integer storyPoints;
    private Progress progress;
    private Long userAssignedId;

}
