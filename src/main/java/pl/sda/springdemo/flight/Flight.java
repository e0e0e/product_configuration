package pl.sda.springdemo.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flight {

    private String from;
    private String to;
    private List<String> connections;

    public Flight(@JsonProperty("from") String from,
                  @JsonProperty("to") String to,
                  @JsonProperty("connections") List<String> connections) {
        this.from = from;
        this.to = to;
        this.connections = connections;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public List<String> getConnections() {
        return connections;
    }
}
