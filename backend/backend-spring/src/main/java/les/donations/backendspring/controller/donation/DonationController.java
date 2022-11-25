package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.file.IFileManagement;
import les.donations.backendspring.service.donation.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class DonationController extends IController implements IDonationController{

    @Autowired
    private DonationService donationService;
    @Autowired
    private IFileManagement fileManagement;

    @Override
    public ResponseEntity<ApiReturnMessage> getDonations() {
        System.out.println("GET Donations");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> getDonation(Long donationId) {
        System.out.println("GET Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonation(DonationDTO donationDTO, MultipartFile[] donationImages) {

        try{

            // saves the donation images in the file system
            List<FileDTO> filesDTO = fileManagement.saveFiles(donationImages, "C:\\Users\\lukyf\\Desktop\\donationsImages");
            // registers the donation
            donationDTO = donationService.registerDonation(donationDTO, filesDTO);
            return created(donationDTO);

            // if any entity does not exist
        }catch (NotFoundEntityException e){
            return notFound(e.getMessage());

            // if the information has any error
        }catch (IllegalArgumentException e){
            return badRequest(e.getMessage());

            // if an error occurred while reading the file
        }catch (IOException e){
            return internalServerError("An error occurred while reading the files!");
        }
    }

    @Override
    public ResponseEntity<ApiReturnMessage> updateDonation(Long donationId, DonationDTO donationDTO) {

        try{

            // updates the donation information
            donationDTO = donationService.updateDonation(donationId, donationDTO);
            return created(donationDTO);

            // if ayn entity does not exist
        }catch (NotFoundEntityException e){
            return notFound(e.getMessage());

            // if the information has any error
        }catch (IllegalArgumentException e){
            return badRequest(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiReturnMessage> deleteDonation(Long donationId) {
        System.out.println("DELETE Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
