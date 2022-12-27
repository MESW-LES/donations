package les.donations.backendspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeographicAreaDTO implements ModelDTO {

    @JsonProperty
    public Long id;
    @JsonProperty
    public String city;
    @JsonProperty
    public String parish;
    @JsonProperty
    public String municipality;

    public GeographicAreaDTO id(Long id) {
        this.id = id;
        return this;
    }

    public GeographicAreaDTO city(String city) {
        this.city = city;
        return this;
    }

    public GeographicAreaDTO parish(String parish) {
        this.parish = parish;
        return this;
    }

    public GeographicAreaDTO municipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

}
