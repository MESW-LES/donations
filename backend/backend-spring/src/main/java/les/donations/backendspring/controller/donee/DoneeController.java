package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.service.donee.IDoneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DoneeController implements IDoneeController {

    @Autowired
    private IDoneeService doneeService;

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonee(DoneeDTO doneeDTO) {

        ApiReturnMessage apiReturnMessage;
        HttpStatus httpStatus;
        try{
            doneeDTO = doneeService.registerDonee(doneeDTO);
            httpStatus = HttpStatus.CREATED;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), doneeDTO);
        }catch (IllegalArgumentException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), e.getMessage());
        }catch (IOException e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), e.getMessage());
        }

        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }
}
