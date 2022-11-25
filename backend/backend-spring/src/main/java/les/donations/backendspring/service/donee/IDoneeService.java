package les.donations.backendspring.service.donee;

import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;

import java.io.IOException;

public interface IDoneeService {

    /**
     * Method that registers a company as a donee and sends a confirmation email
     * @param doneeDTO the donee information
     * @return the information related to the registered donee
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws NotFoundEntityException
     * @throws NotFoundEntityException
     */
    DoneeDTO registerDonee(DoneeDTO doneeDTO) throws IllegalArgumentException, IOException, NotFoundEntityException;

}
