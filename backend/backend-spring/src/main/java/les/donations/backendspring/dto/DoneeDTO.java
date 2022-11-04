package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DoneeDTO implements ModelDTO{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public CompanyDTO company;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public List<String> geographicAreaIds;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    public List<String> categoryIds;

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

    public DoneeDTO categoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
        return this;
    }
}
