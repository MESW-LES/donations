package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String nif;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String password;

    public PersonDTO id(Long id) {
        this.id = id;
        return this;
    }

    public PersonDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonDTO nif(String nif) {
        this.nif = nif;
        return this;
    }

    public PersonDTO address(String address) {
        this.address = address;
        return this;
    }

    public PersonDTO email(String email) {
        this.email = email;
        return this;
    }

    public PersonDTO password(String password) {
        this.password = password;
        return this;
    }
}
