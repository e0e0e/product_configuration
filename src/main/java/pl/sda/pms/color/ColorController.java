package pl.sda.pms.color;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/color/create")
    public String createColor(@RequestParam String name, @RequestParam String ral, @RequestParam String hex,
            Model model) {
        try {
            colorService.create(name, ral, hex);
        } catch (Exception e) {
            System.out.println("problem with color creation: " + e.getMessage());
        }

        return "redirect:/color/list";

    }

    @GetMapping("/color/list")
    public String listColors(Model model) {

        model.addAttribute("colors", colorService.findAll());

        model.addAttribute("title", "List colors");
        model.addAttribute("path", "color/list");
        return "main";

    }
    @GetMapping("/color/add")
    public String addColor(Model model) {

        model.addAttribute("title", "List colors");
        model.addAttribute("path", "color/create");
        return "main";

    }

}