package pl.sda.springdemo.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();


    Optional<User> findByUsername(String username);
    @Query(value = "SELECT * FROM user WHERE email like %?1%", nativeQuery = true)
    List<User> findUsersByEmail(String filterUserByEmail);
}
