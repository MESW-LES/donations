package les.donations.backendspring.controller.geographicArea;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.geographicArea.IGeographicAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class GeographicAreaController extends IController implements IGeographicAreaController {
    @Autowired
    private IGeographicAreaService geographicAreaService;

    @Override
    public ResponseEntity<ApiReturnMessage> registerGeographicArea(GeographicAreaDTO geographicAreaDTO) {
        try {
            geographicAreaDTO = geographicAreaService.registerGeographicArea(geographicAreaDTO);
            return created(geographicAreaDTO);
        } catch (IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiReturnMessage> getGeographicAreas() {
        PaginationDTO areaDTOs = geographicAreaService.getGeographicAreas();
        return ok(areaDTOs);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> deleteGeographicArea(Long geographicAreaId) {
        try {
            GeographicAreaDTO geographicAreaDTO = geographicAreaService.deleteGeographicArea(geographicAreaId);
            return ok(geographicAreaDTO);
        } catch (NotFoundEntityException e) {
            return notFound(e.getMessage());
        } catch (IllegalArgumentException e) {
            return badRequest(e.getMessage());
        }
    }
}
