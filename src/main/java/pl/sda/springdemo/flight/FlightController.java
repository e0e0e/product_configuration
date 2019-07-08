package pl.sda.springdemo.flight;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public String showFlightForm() {
        return "flight/search";
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam String from,
                                @RequestParam String to, Model model) {
        model.addAttribute("flights", flightService.find(from, to).getFlights());

        return "flight/search";
    }
}
