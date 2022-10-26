package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IDonorController {

    @PostMapping(value = "/donors", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerDonor(@RequestBody DonorDTO donorDTO);
}
