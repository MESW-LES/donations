package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String taxNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long phone;

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
}
