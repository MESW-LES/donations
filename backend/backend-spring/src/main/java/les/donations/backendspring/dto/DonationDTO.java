package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DonationDTO implements ModelDTO{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long donorId;

    public DonationDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DonationDTO name(String name) {
        this.name = name;
        return this;
    }

    public DonationDTO donorId(Long donorId) {
        this.donorId = donorId;
        return this;
    }
}
