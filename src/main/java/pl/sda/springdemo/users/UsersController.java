package pl.sda.springdemo.users;


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


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers() {

        return "users/users";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String login,
                          @RequestParam String userName,
                          Model model) {
        model.addAttribute("loggedUser", userService.getLogged());
        try {

            //dodanie usera
            boolean result = userService.create( login,  password,  email,  userName);


            model.addAttribute("createUserResult", result);
            List<User> users = userService.findAll();
            model.addAttribute("users", users);

            //System.out.println(email + " " + password + " " + datfB);
            return "users/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "users/users";
        }
    }

    @GetMapping("users/delete")
    public String deleteUser(@RequestParam long userId,
                             Model model) {
        if(userService.getLogged()==null){
            return "users/login";
        }
        userService.delete(userId);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("deleteUserResults", true);
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/list";

    }

    @GetMapping("users/list")
    public String listUsers(Model model) {
        if(userService.getLogged()==null){
            return "users/login";
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", userService.getLogged());
        return "users/list";
    }
}
