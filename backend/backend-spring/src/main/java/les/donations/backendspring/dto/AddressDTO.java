package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO implements ModelDTO {
    @JsonProperty
    private Long id;

    @JsonProperty
    public String street;

    @JsonProperty
    public Long houseNumber;

    @JsonProperty
    public String postalCode;

    @JsonProperty
    public Long geographicArea;

    public AddressDTO id(Long id) {
        this.id = id;
        return this;
    }

    public AddressDTO street(String street) {
        this.street = street;
        return this;
    }

    public AddressDTO houseNumber(Long houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public AddressDTO postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressDTO geographicArea(Long geographicAreaId) {
        this.geographicArea = geographicAreaId;
        return this;
    }
}
