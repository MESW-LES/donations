package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.service.donor.IDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        Object message;
        HttpStatus httpStatus;
        try {
            message = donorService.addDonor(donorDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        final ApiReturnMessage apiReturnMessage = new ApiReturnMessage(httpStatus.value(), message);
        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }
}
