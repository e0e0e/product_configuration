package pl.sda.springdemo.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.springdemo.users.User;

@Repository
public interface SprintRepositoryJPA extends JpaRepository<Sprint,Long> {

    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findUserByName(String login);
}
