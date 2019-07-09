package pl.sda.springdemo.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class ProjectParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long projectId;
    private List<Long> userId;


}
