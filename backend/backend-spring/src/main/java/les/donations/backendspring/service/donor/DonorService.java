package les.donations.backendspring.service.donor;


import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.model.Person;
import les.donations.backendspring.repository.donor.IDonorRepository;
import les.donations.backendspring.repository.person.IPersonRepository;
import les.donations.backendspring.service.person.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DonorService implements IDonorService{

    @Autowired
    private IDonorRepository donorRepository;

    @Autowired
    private IPersonService personService;

    @Override
    public DonorDTO registerDonor(DonorDTO donorDTO) throws IllegalArgumentException, IOException {
        // creates the person
        Person person = personService.addPerson(donorDTO.person);
        // creates the donor
        Donor donor = new Donor(person);
        // persists the donor
        donor = donorRepository.saveAndFlush(donor);

        // TODO: sends the email
        String personEmail = donor.getPerson().getEmail();

        return donorDTO.id(donor.getId());
    }
}
