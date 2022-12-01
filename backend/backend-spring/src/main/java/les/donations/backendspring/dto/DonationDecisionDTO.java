package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationDecisionDTO {

    @JsonProperty
    public Boolean decision;

    public DonationDecisionDTO decision(Boolean decision) {
        this.decision = decision;
        return this;
    }
}
