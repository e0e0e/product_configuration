package pl.sda.springdemo.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FlightResponse {

    private List<Flight> flights;

    @JsonCreator
    public FlightResponse(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
