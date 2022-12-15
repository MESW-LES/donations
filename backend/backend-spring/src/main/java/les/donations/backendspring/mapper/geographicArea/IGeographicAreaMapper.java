package les.donations.backendspring.mapper.geographicArea;

import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.model.GeographicArea;

public interface IGeographicAreaMapper {
    /**
     * Method that centers the process of instantiating a GeographicAreaDTO through is correspondent geographicArea
     * @param geographicArea the entity to be converted
     * @return the geographicAreaDTO created
     * @throws IllegalArgumentException if there is an error in geographicArea data
     */
    GeographicAreaDTO modelToDTO(GeographicArea geographicArea) throws IllegalArgumentException;

    /**
     * Method that centers the process of instantiating a GeographicArea through is correspondent DTO
     * @param geographicAreaDTO the DTO to be converted
     * @return the geographicArea created
     * @throws IllegalArgumentException if there is an error in geographicArea data
     */
    GeographicArea dtoToModel(GeographicAreaDTO geographicAreaDTO) throws IllegalArgumentException;
}
