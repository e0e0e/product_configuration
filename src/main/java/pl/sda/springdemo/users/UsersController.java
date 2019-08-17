package pl.sda.springdemo.users;


import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {

        return "user/users";
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/taskWall";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String login,
                          @RequestParam String username,
                          Model model) {
        try {
            //dodanie usera
            boolean result = userService.create(login, passwordEncoder.encode(password), email, username);
            model.addAttribute("createUserResult", result);
            List<User> users = userService.findAll();
            model.addAttribute("users", users);

            //System.out.println(email + " " + password + " " + datfB);
            return "redirect:/taskWall";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "user/users";
        }
    }

    @GetMapping("users/delete")
    public String deleteUser(@RequestParam long userId,
                             Model model) {

        userService.delete(userId);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("deleteUserResults", true);
//        model.addAttribute("loggedUser", userService.getLogged());
        return "user/list";

    }

    @GetMapping("users/list")
    public String listUsers(Model model) {

        model.addAttribute("users", userService.findAll());
//        model.addAttribute("loggedUser", userService.getLogged());
        return "user/list";
    }

    @GetMapping("/userProfile")
    public String MyProfile(@RequestParam(required = false) Long userId,
                            @RequestParam(required = false) String username,
                            Model model) {

        if (username != null) {
            model.addAttribute("user", userService.findUserByname(username));
        } else {
            model.addAttribute("user", userService.findById(userId));
        }
        model.addAttribute("title", "User Profile");
        model.addAttribute("path", "user/userProfile");
        return "main";
    }
}
