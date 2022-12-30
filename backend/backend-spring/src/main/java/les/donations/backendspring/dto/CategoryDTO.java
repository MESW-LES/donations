package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDTO implements ModelDTO{
    @JsonProperty
    public String code;
    @JsonProperty
    public String name;
    @JsonProperty
    public String description;
    @JsonProperty
    public Boolean active;

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

    public CategoryDTO active(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
