package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DemoDTO {

    @JsonProperty
    public Long id;
    @JsonProperty
    public String description;

    public DemoDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DemoDTO description(String description) {
        this.description = description;
        return this;
    }
}
