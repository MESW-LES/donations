package les.donations.backendspring.controller.geographicArea;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.GeographicAreaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IGeographicAreaController {
    /**
     * Creates a geographic area
     * @param geographicAreaDTO, containing information about the geographic area
     * @return a response with a code which represents if the operation was successful or not
     */
    @PostMapping(value = "/geographicAreas", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerGeographicArea(@RequestBody GeographicAreaDTO geographicAreaDTO);

    /**
     * Method that gets all geographic areas in the system
     * @return a response with a code which represents if the operation was successful or not
     */
    @GetMapping(value = "/geographicAreas", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getGeographicAreas();

    /**
     * Method deletes geographic area by given id
     * @param geographicAreaId is id of geographic area that should be deleted
     * @return a response with a code which represents if the operation was successful or not
     */
    @DeleteMapping(value = "/geographicAreas/{id}", produces = "application/json")
    ResponseEntity<ApiReturnMessage> deleteGeographicArea(@PathVariable("id") Long geographicAreaId);
}
