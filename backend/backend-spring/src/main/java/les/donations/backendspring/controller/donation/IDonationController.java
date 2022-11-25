package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface IDonationController {

    @GetMapping(value = "/donations", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getDonations();

    @GetMapping(value = "/donations/{id}", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getDonation(@PathVariable("id") Long donationId);

    @PostMapping(value = "/donations", produces = "application/json", consumes = "multipart/form-data")
    ResponseEntity<ApiReturnMessage> registerDonation(@ModelAttribute DonationDTO donationDTO, @RequestPart("donationImages") MultipartFile [] donationImages);

    @PutMapping(value = "/donations/{id}", produces = "application/json", consumes = "multipart/form-data")
    ResponseEntity<ApiReturnMessage> updateDonation(@PathVariable("id") Long donationId, @ModelAttribute DonationDTO donationDTO);

    @DeleteMapping(value = "/donations/{id}", produces = "application/json")
    ResponseEntity<ApiReturnMessage> deleteDonation(@PathVariable("id") Long donationId);

}
