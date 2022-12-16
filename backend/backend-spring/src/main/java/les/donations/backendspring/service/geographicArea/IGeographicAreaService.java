package les.donations.backendspring.service.geographicArea;

import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.GeographicArea;

import java.io.IOException;

public interface IGeographicAreaService {
    /**
     * Creates a geographicArea
     * @param geographicAreaDTO, containing information about the geographicArea
     * @return an information about the geographicArea if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, fields are not unique
     */
    GeographicAreaDTO registerGeographicArea(GeographicAreaDTO geographicAreaDTO) throws IllegalArgumentException;

    /**
     * Method that gets all the geographic areas in the system
     * @return information about the geographic areas
     */
    PaginationDTO getGeographicAreas();

    /**
     * Method that removes specific geographic area
     * @param geographicAreaId the area's identification
     * @throws NotFoundEntityException if the geographic area does not exist in the system
     * @throws IllegalArgumentException if the geographic area cant be deleted
     */
    GeographicAreaDTO deleteGeographicArea(Long geographicAreaId) throws NotFoundEntityException, IllegalArgumentException;

    GeographicArea getGeographicAreaModel(Long id) throws NotFoundEntityException;
}
