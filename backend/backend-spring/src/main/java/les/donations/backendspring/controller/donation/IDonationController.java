package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface IDonationController {

    @GetMapping(value = "/donations", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getDonations();

    @GetMapping(value = "/donations/{id}", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getDonation(@PathVariable("id") Long donationId);

    @PostMapping(value = "/donations", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerDonation();

    @PutMapping(value = "/donations/{id}", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> updateDonation(@PathVariable("id") Long donationId);

    @DeleteMapping(value = "/donations/{id}", produces = "application/json")
    ResponseEntity<ApiReturnMessage> deleteDonation(@PathVariable("id") Long donationId);

}
