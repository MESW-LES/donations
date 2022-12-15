package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO implements ModelDTO {

    @JsonProperty
    public String email;
    @JsonProperty
    public String role;

    public UserDTO email(String email) {
        this.email = email;
        return this;
    }

    public UserDTO role(String role) {
        this.role = role;
        return this;
    }
}
