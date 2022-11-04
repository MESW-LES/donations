package les.donations.backendspring.service.donee;

import les.donations.backendspring.dto.DoneeDTO;

import java.io.IOException;

public interface IDoneeService {

    DoneeDTO registerDonee(DoneeDTO doneeDTO) throws IllegalArgumentException, IOException;

}
