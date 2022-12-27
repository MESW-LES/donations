package les.donations.backendspring.mapper.geographicArea;

import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.model.GeographicArea;
import org.springframework.stereotype.Component;

@Component
public class GeographicAreaMapper implements IGeographicAreaMapper {
    @Override
    public GeographicArea dtoToModel(final GeographicAreaDTO geographicAreaDTO) throws IllegalArgumentException {
        return new GeographicArea(geographicAreaDTO.city, geographicAreaDTO.parish,
                geographicAreaDTO.municipality);
    }

    @Override
    public GeographicAreaDTO modelToDTO(final GeographicArea geographicArea) {
        return new GeographicAreaDTO().city(geographicArea.getCity()).
                parish(geographicArea.getParish()).
                municipality(geographicArea.getMunicipality());
    }
}
