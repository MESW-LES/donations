package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.donee.IDoneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class DoneeController extends IController implements IDoneeController {

    @Autowired
    private IDoneeService doneeService;

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonee(DoneeDTO doneeDTO) {

        try{
            doneeDTO = doneeService.registerDonee(doneeDTO);
            return created(doneeDTO);
        }catch (IllegalArgumentException e){
            return badRequest(e.getMessage());
        }catch (IOException e){
            return internalServerError(e.getMessage());
        } catch (NotFoundEntityException e) {
            return notFound(e.getMessage());
        }
    }
}
