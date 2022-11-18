package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationDTO implements ModelDTO {

    @JsonProperty
    public Long id;
    @JsonProperty
    public String title;
    @JsonProperty
    public String description;
    @JsonProperty
    public List<String> categoriesCode;
    @JsonProperty
    public Long addressId;

    public DonationDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DonationDTO title(String title) {
        this.title = title;
        return this;
    }

    public DonationDTO description(String description) {
        this.description = description;
        return this;
    }

    public DonationDTO categoriesCode(List<String> categoriesCode) {
        this.categoriesCode = categoriesCode;
        return this;
    }

    public DonationDTO addressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }
}
