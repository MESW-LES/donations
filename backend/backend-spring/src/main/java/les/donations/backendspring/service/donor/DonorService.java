package les.donations.backendspring.service.donor;


import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.address.IAddressMapper;
import les.donations.backendspring.model.Address;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.model.Person;
import les.donations.backendspring.repository.donor.DonorDao;
import les.donations.backendspring.service.address.IAddressService;
import les.donations.backendspring.service.person.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DonorService implements IDonorService{

    @Autowired
    private DonorDao donorRepository;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private IAddressService addressService;

    @Override
    public DonorDTO registerDonor(DonorDTO donorDTO) throws IllegalArgumentException, IOException, NotFoundEntityException {
        // creates the person
        Person person = personService.addPerson(donorDTO.person);
        // creates the donor
        Donor donor = new Donor(person);
        // persists the donor
        donor = donorRepository.saveAndFlush(donor);

        return donorDTO.id(donor.getId());
    }
}
