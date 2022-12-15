package les.donations.backendspring.service.donee;

import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.model.Person;
import les.donations.backendspring.repository.donor.DonorDao;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.service.donor.DonorService;
import les.donations.backendspring.service.person.IPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DonorServiceTest {

    @Mock
    private DonorDao donorRepository;
    @Mock
    private IPersonService personService;
    @InjectMocks
    private DonorService donorService;


    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDonorWithErrorsInPersonInformationTest() throws IOException {
        DonorDTO donorDTO = new DonorDTO().id(1L);
        when(personService.addPerson(donorDTO.person)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> donorService.registerDonor(donorDTO));
    }

    @Test
    void registerCorrectDonorTest() throws IOException {
        Person person = new Person("name", "lastName", "987654321",
                "address", "joe.doe@gmail.com");
        Donor donor = new Donor();
        donor.setId(1L);
        donor.setPerson(person);
        PersonDTO personDTO = new PersonDTO();
        DonorDTO donorDTO = new DonorDTO().id(2L).person(personDTO);

        when(personService.addPerson(donorDTO.person)).thenReturn(person);
        when(donorRepository.saveAndFlush(Mockito.any(Donor.class))).thenReturn(donor);
        DonorDTO returned = donorService.registerDonor(donorDTO);

        assertEquals(1L, returned.id);
    }
}