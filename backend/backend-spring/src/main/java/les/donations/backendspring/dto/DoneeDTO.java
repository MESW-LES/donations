package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoneeDTO implements ModelDTO{

    @JsonProperty
    public Long id;
    @JsonProperty
    public String password;
    @JsonProperty
    public CompanyDTO company;
    @JsonProperty
    public List<String> geographicAreaIds;
    @JsonProperty
    public List<String> categoryCodes;

    public DoneeDTO id(Long id) {
        this.id = id;
        return this;
    }

    public DoneeDTO company(CompanyDTO company) {
        this.company = company;
        return this;
    }

    public DoneeDTO geographicAreaIds(List<String> geographicAreaIds) {
        this.geographicAreaIds = geographicAreaIds;
        return this;
    }

    public DoneeDTO categoryCodes(List<String> categoryCodes) {
        this.categoryCodes = categoryCodes;
        return this;
    }

    public DoneeDTO password(String password) {
        this.password = password;
        return this;
    }
}
