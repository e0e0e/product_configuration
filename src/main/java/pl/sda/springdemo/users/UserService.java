package pl.sda.springdemo.users;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final static Map<String, User> usersByEmail = new HashMap<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean create(String email, String password, LocalDate datfB) {
        boolean isEmailTaken=userRepository.findAll().stream()
                .anyMatch(u->u.getEmail().equals(email));

        if (isEmailTaken){
            throw new RuntimeException("Email already in use");
        }
        User user = new User(email, password, datfB);
        User created = userRepository.save(user);

        //usersByEmail.put(user.getEmail(), user);

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
}
