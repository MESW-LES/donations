package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.service.donor.IDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class DonorController implements IDonorController {
    @Autowired
    private IDonorService donorService;


    /**
     * Creates a donor
     * @param donorDTO, containing information about the donor
     * @return a response with a code which represents if the operation was successful or not
     */
    @Override
    public ResponseEntity<ApiReturnMessage> registerDonor(DonorDTO donorDTO) {
        HttpStatus httpStatus;
        ApiReturnMessage apiReturnMessage;

        try {
            donorDTO = donorService.registerDonor(donorDTO);
            httpStatus = HttpStatus.CREATED;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), donorDTO);
        } catch (IllegalArgumentException ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), ex.getMessage());
        } catch (IOException ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), ex.getMessage());
        }

        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }
}
