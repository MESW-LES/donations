package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DonorDTO {

    @JsonProperty
    public Long id;

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public String nif;

    @JsonProperty
    public String address;

    @JsonProperty
    public String email;

    @JsonProperty
    public String password;

    public DonorDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DonorDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DonorDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public DonorDTO nif(String nif) {
        this.nif = nif;
        return this;
    }

    public DonorDTO address(String address) {
        this.address = address;
        return this;
    }

    public DonorDTO email(String email) {
        this.email = email;
        return this;
    }

    public DonorDTO password(String password) {
        this.password = password;
        return this;
    }
}
