package pl.sda.pms.task;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class SmallTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Integer size;

    public SmallTask(Integer size) {
        this.size = size;
    }

    public SmallTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
