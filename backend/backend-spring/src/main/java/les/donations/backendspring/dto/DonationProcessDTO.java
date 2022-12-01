package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationProcessDTO implements ModelDTO{

    @JsonProperty
    public String ongoingDate;
    @JsonProperty
    public String status;
    @JsonProperty
    public String donee;

    public DonationProcessDTO ongoingDate(String ongoingDate) {
        this.ongoingDate = ongoingDate;
        return this;
    }

    public DonationProcessDTO status(String status) {
        this.status = status;
        return this;
    }

    public DonationProcessDTO donee(String donee) {
        this.donee = donee;
        return this;
    }
}
