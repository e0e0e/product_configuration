package pl.sda.springdemo.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.sda.springdemo.users.User;
import pl.sda.springdemo.users.UserRepository;


import java.util.Collections;

@Component
class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Invalid username"));


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
            Collections.emptyList());
    }
}
/**/