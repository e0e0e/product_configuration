package pl.sda.springdemo.flight;

import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;

@Service
public class FlightService {

    public FlightResponse find(String from, String to) {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());


        HttpResponse<FlightResponse> response = Unirest.get("http://localhost:9090/flights")
            .queryString("from", from)
            .queryString("to", to)
            .asObject(FlightResponse.class);

        return response.getBody();
    }
}
