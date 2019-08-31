package pl.sda.pms.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.sda.pms.users.User;
import pl.sda.pms.users.UserRepository;


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


//        MyUserDetails myUserDetails=new MyUserDetails();
//        myUserDetails.setUser(user);


        //       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//            Collections.emptyList());
      return new MyUserDetails(user);
        }
    }

/**/