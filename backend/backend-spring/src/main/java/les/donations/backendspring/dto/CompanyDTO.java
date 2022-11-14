package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO {

    @JsonProperty
    public Long id;
    @JsonProperty
    public String name;
    @JsonProperty
    public String description;
    @JsonProperty
    public String taxNumber;
    @JsonProperty
    public Long phone;
    @JsonProperty
    public List<String> categoryId;

    public CompanyDTO id(Long id) {
        this.id = id;
        return this;
    }

    public CompanyDTO name(String name) {
        this.name = name;
        return this;
    }

    public CompanyDTO description(String description) {
        this.description = description;
        return this;
    }

    public CompanyDTO taxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
        return this;
    }

    public CompanyDTO phone(Long phone) {
        this.phone = phone;
        return this;
    }
    public CompanyDTO categoryId(List<String> categoryId) {
        this.categoryId = categoryId;
        return this;
    }

}
