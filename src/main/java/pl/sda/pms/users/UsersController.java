package pl.sda.pms.users;


import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:/taskWall";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String password,
                          @RequestParam String email,
                          @RequestParam String login,
                          @RequestParam String username,
                          Model model) {
        try {
            boolean result = userService.create(login, passwordEncoder.encode(password), email, username);
            model.addAttribute("createUserResult", result);
            List<User> users = userService.findAll();
            model.addAttribute("users", users);

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
        return "user/list";

    }

    @GetMapping("users/list")
    public String listUsers(Model model) {

        model.addAttribute("users", userService.findAll());
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

    @GetMapping("/avatars")
    public String showAvatars(Model model) throws IOException {
        Set<String> filesList=listFilesUsingFileWalk("src/main/webapp/resources/images/icons/png/");



        model.addAttribute("fileList", filesList);
        model.addAttribute("title", "Avatars");
        model.addAttribute("path", "user/avatars");
        return "main";
    }

    public Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                if (!Files.isDirectory(file)) {
                    fileList.add(file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }

    public Set<String> listFilesUsingFileWalk(String dir) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }
}
