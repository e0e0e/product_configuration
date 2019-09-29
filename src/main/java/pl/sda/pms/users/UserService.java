package pl.sda.pms.users;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private User logged = null;


    private final static Map<String, User> usersByEmail = new HashMap<>();

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    public boolean create(String login, String password, String email, String userName) {
        boolean isEmailTaken = userRepository.isEmailTaken(email)>0;

        if (isEmailTaken) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(login, password, email, userName);
        User created = userRepository.save(user);


        return created.getId() != null;
    }

    public List<User> findAll() {

        return userRepository.findAll();

    }

    public void delete(long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        userRepository.delete(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }


    public void save(User user) {
        userRepository.save(user);
    }

//    public void login(String userName, String password) {
//        logged = userRepository.findAll().stream()
//                .filter(e -> (e.getUsername().equals(userName) && e.getPassword().equals(password)))
//                .findAny()
//                .orElse(null);
//
//        // return user;
//
//    }
//
//    public User getLogged() {
//        return logged;
//    }
//
//    public void logout() {
//        logged = null;
//    }


    public List<User> findUsersByEmail(String filterUserByEmail) {


        return userRepository.findUsersByEmail(filterUserByEmail);
    }

    public User findUserByname(String username) {

        return userRepository.findByUsername(username).get();
    }

    public List<User> findAllWithException(String loggedUserName) {

        return userRepository.findAllWithException(loggedUserName);
    }

    public List<User> findUsersByEmailWithException(String filterUserByEmail, String loggedUserName) {

    return userRepository
            .findUsersByEmailWithException(filterUserByEmail,loggedUserName);
    }

    public void addAvatar(long userId, String image) {
        userRepository.addAvatar(userId,image);
    }

    public void saveChanges(long userId, String login, String password, String email, String username) {

        boolean isEmailTaken = userRepository.isEmailTakenExcept(email, userId)>0;

        if (isEmailTaken) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(login, password, email, username);
        User created = userRepository.save(user);

//    userRepository.saveChanges(userId, login, password, email, username);

    }
}
