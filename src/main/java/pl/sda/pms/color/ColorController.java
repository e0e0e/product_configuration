package pl.sda.pms.color;

import java.util.stream.Collectors;

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
            @RequestParam String type, Model model) {
        try {
            colorService.create(name, ral, hex, type);
        } catch (Exception e) {
            System.out.println("problem with color creation: " + e.getMessage());
        }

        return "redirect:/color/list";

    }

    @PostMapping("/color/change")
    public String changeColor(@RequestParam String name, @RequestParam String ral, @RequestParam String hex,
            @RequestParam String type, @RequestParam Long colorId, Model model) {
        try {
            colorService.edit(colorId, name, ral, hex, type);
        } catch (Exception e) {
            System.out.println("problem with color edition: " + e.getMessage());
        }

        return "redirect:/color/list";

    }

    @GetMapping("/color/edit")
    public String editColors(@RequestParam Long colorId, Model model) {

        model.addAttribute("color", colorService.findById(colorId));

        model.addAttribute("title", "List colors");
        model.addAttribute("path", "color/edit");
        return "main";

    }

    @GetMapping("/color/list")
    public String listColors(Model model) {

        model.addAttribute("colors", colorService.findAll().stream().sorted((o1,o2)->o1.getName().compareTo(o2.getName())).collect(Collectors.toList()));

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