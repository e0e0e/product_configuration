package pl.sda.springdemo.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

class MyBody {

    @JsonProperty
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
