package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CategoryDTO implements ModelDTO{
    @JsonProperty
    public String code;

    @JsonProperty
    public String name;

    @JsonProperty
    public String description;

    public CategoryDTO code(String code) {
        this.code = code;
        return this;
    }

    public CategoryDTO name(String name) {
        this.name = name;
        return this;
    }

    public CategoryDTO description(String description) {
        this.description = description;
        return this;
    }
}
