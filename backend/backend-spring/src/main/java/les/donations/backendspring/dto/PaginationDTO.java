package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationDTO implements ModelDTO{

    @JsonProperty
    public List<ModelDTO> results;
    @JsonProperty
    public Integer countResults;

    public PaginationDTO results(List<ModelDTO> results) {
        this.results = results;
        return this;
    }

    public PaginationDTO countResults(Integer countResults) {
        this.countResults = countResults;
        return this;
    }
}
