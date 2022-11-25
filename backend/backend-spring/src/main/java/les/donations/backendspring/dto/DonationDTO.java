package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationDTO implements ModelDTO {

    @JsonProperty
    public Long id;
    @JsonProperty
    public String title;
    @JsonProperty
    public String description;
    @JsonProperty
    public String categoriesCode;
    @JsonProperty
    public Long addressId;
    @JsonProperty
    public String createdDate;

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

    public DonationDTO categoriesCode(String categoriesCode) {
        this.categoriesCode = categoriesCode;
        return this;
    }

    public DonationDTO addressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public DonationDTO createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoriesCode() {
        return categoriesCode;
    }

    public void setCategoriesCode(String categoriesCode) {
        this.categoriesCode = categoriesCode;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
