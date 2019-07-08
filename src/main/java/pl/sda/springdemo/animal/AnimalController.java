package pl.sda.springdemo.animal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AnimalController {

    private final AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/animal/{id}")
    public String getAnimal(@PathVariable Long id, Model model) {
        Animal animal = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));
        model.addAttribute("animal", animal);

        return "animal/details";
    }

    @PostMapping(value = "/animals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getAnimals(@RequestBody Animal animal, Model model) {

        repository.save(animal);
        model.addAttribute("animals", repository.findAll());
        return "animal/list";
    }
}
