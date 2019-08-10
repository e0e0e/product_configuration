package pl.sda.springdemo.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

    List<Task> findAll();

    @Query(value = "SELECT * FROM task WHERE progress like 0", nativeQuery = true)
    List<Task> findToDO();
}
