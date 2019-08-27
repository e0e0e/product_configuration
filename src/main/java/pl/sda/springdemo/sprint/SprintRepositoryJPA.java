package pl.sda.springdemo.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.springdemo.users.User;

import java.util.List;

@Repository
public interface SprintRepositoryJPA extends JpaRepository<Sprint, Long> {

    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findUserByName(String login);

    @Query(value = "SELECT * FROM sprint limit 1", nativeQuery = true)
    Sprint getSprint();

    @Query(value = "SELECT * FROM sprint order by start_date", nativeQuery = true)
    List<Sprint> findAllSprintsSorted();

    @Query(value = "SELECT top 1 id FROM sprint where start_date>(Select start_date from sprint where id like ?1) order by start_date asc ", nativeQuery = true)
    Long findNextSprint(long sprintId);

    @Query(value = "SELECT top 1 id FROM sprint where start_date<(Select start_date from sprint where id like ?1) order by start_date desc", nativeQuery = true)
    Long findPreviousSprint(long sprintId);
}
