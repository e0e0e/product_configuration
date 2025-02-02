package pl.sda.pms.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();


    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE email like %?1%", nativeQuery = true)
    List<User> findUsersByEmail(String filterUserByEmail);

    @Query(value = "SELECT * FROM user WHERE username != ?1", nativeQuery = true)
    List<User> findAllWithException(String loggedUserName);

    @Query(value = "SELECT * FROM user WHERE email like %?1% and username Not like ?2", nativeQuery = true)
    List<User> findUsersByEmailWithException(String filterUserByEmail, String loggedUserName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user set avatar=?2 where id like ?1", nativeQuery = true)
    void addAvatar(long userId, String image);


    @Query(value = "SELECT count(email) FROM user WHERE EMAIL like ?1", nativeQuery = true)
    int isEmailTaken(String email);

//    void saveChanges(long userId, String login, String password, String email, String username);

    @Query(value = "SELECT count(email) FROM user WHERE EMAIL like ?1 and id!=?2", nativeQuery = true)
    int isEmailTakenExcept(String email, long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user set authorities=?2 where id like ?1", nativeQuery = true)
	void changeAuthority(long userId, String authorities);
}
