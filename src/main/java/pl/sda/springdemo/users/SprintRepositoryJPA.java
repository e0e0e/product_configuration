package pl.sda.springdemo.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepositoryJPA extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE user_name = ?1", nativeQuery = true)
    User findUserByName(String login);
}
