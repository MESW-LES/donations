package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class DonorDTO implements ModelDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public PersonDTO person;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public AddressDTO address;

    public DonorDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DonorDTO person(PersonDTO person) {
        this.person = person;
        return this;
    }

    public DonorDTO address(AddressDTO address) {
        this.address = address;
        return this;
    }

}
