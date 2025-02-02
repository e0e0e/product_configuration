package pl.sda.pms.users;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.pms.config.MyUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        return "redirect:/orders/list";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String password, @RequestParam String passwordConfirm,
            @RequestParam String email, Model model) {
        if (password.equals(passwordConfirm) && password.length()>5) {
            try {
                String login = email.split("@")[0];
                String username = email.split("@")[0];
                boolean result = userService.create(login, passwordEncoder.encode(password), email, username);
                model.addAttribute("createUserResult", result);
                List<User> users = userService.findAll();
                model.addAttribute("users", users);

                return "redirect:/";
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getLocalizedMessage());
                return "user/users";
            }

        } else {
            model.addAttribute("errorMessage","Different passwords, or passwort shorter than 6 letters");
            return "user/users";

        }

    }

    @GetMapping("user/delete")
    public String deleteUser(@RequestParam long userId, Model model) {

        userService.delete(userId);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("deleteUserResults", true);
        return "redirect:/users/list";

    }

    @GetMapping("/user/edit")
    public String editUser(@RequestParam long userId, Model model) {
        try {
            model.addAttribute("user", userService.findById(userId));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
        }
        model.addAttribute("title", "Edit User");
        model.addAttribute("path", "user/editUser");
        return "main";
    }

    @GetMapping("saveChangedUser")
    public String saveChangedUser(@RequestParam String password, @RequestParam String email, @RequestParam String login,
            @RequestParam String username, @RequestParam long userId, Model model) {

        // try {
        // userService.saveChanges(userId,login, passwordEncoder.encode(password),
        // email, username);
        //
        //
        // } catch (Exception e) {
        //
        //
        // }

        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "User List");
        model.addAttribute("path", "user/list");
        return "main";
    }

    @GetMapping("users/list")
    public String listUsers(Model model) {

        model.addAttribute("users", userService.findAll());
        model.addAttribute("title", "User List");
        model.addAttribute("path", "user/list");
        return "main";
    }

    @GetMapping("accessDenied")
    private String forbidenAccess(Model model) {

        model.addAttribute("title", "User List");
        model.addAttribute("path", "accessDenied");
        return "main";

    }

    @GetMapping("/userProfile")
    public String MyProfile(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) String username, Model model) {

        if (username != null) {
            model.addAttribute("user", userService.findUserByname(username));
        } else {
            model.addAttribute("user", userService.findById(userId));
        }
        model.addAttribute("title", "User Profile");
        model.addAttribute("path", "user/userProfile");
        return "main";
    }

    @GetMapping("/avatars")
    public String showAvatars(Model model) throws IOException {
        Set<String> filesList = listFilesUsingFileWalk("src/main/webapp/resources/images/icons/png/");

        model.addAttribute("fileList", filesList);
        model.addAttribute("title", "Avatars");
        model.addAttribute("path", "user/avatars");
        return "main";
    }

    @GetMapping("/users/addAvatars")
    public String addAvatar(@RequestParam String image, @RequestParam long userId, HttpServletResponse response,
            HttpServletRequest request, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        myUserDetails.setAvatar(image);

        userService.addAvatar(userId, image);
        return "redirect:/userProfile?userId=" + userId;
    }

    public static Set<String> listFilesUsingFileWalk(String dir) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir))) {
            return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    @GetMapping("/auth/user/edit")
    public String showAuthoritiesForm(@RequestParam long userId, Model model) {

        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("title", "User Authorities");
        model.addAttribute("path", "user/authoriti");
        return "main";
    }

    @PostMapping("/auth/user/save")
    public String changeAuthorities(@RequestParam Long userId, @RequestParam String authorities, Model model) {

        userService.changeAuthority(userId, authorities);

        return "redirect:/users/list";
    }

}
