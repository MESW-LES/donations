package les.donations.backendspring.service.donor;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.mapper.donor.IDonorMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.repository.category.ICategoryRepository;
import les.donations.backendspring.repository.donor.IDonorRepository;
import les.donations.backendspring.service.category.CategoryService;
import liquibase.pro.packaged.D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DonorServiceTest {

    @Mock
    private IDonorRepository donorRepository;
    @Mock
    private IDonorMapper donorMapper;
    @InjectMocks
    private DonorService donorService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addDonorWithWrongPropertyInformationInformation() {
        DonorDTO donorDTO = new DonorDTO().firstName("First name");
        when(donorMapper.dtoToModel(donorDTO)).thenThrow(new IllegalArgumentException("The last name can't be null or empty"));

        try{
            donorService.addDonor(donorDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The last name can't be null or empty", e.getMessage());
        }
    }

    @Test
    void addDonorWithWExistingNif(){
        DonorDTO donorDTO = new DonorDTO();
        when(donorMapper.dtoToModel(donorDTO)).thenReturn(new Donor());
        when(donorRepository.saveAndFlush(new Donor())).thenThrow(new IllegalArgumentException("NIF or email already exists"));

        try {
            donorService.addDonor(donorDTO);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("NIF or email already exists", e.getMessage());
        }
    }

    @Test
    void addDonorWithValidInformation(){
        DonorDTO donorDTO = new DonorDTO().id(Long.valueOf("1")).firstName("first").lastName("last").nif("123456789").address("address")
                        .email("email@gmail.com").password("password");
        when(donorMapper.dtoToModel(donorDTO)).thenReturn(new Donor());
        when(donorRepository.saveAndFlush(new Donor())).thenReturn(new Donor(Long.valueOf("1"), "first", "last",
                "123456789", "address", "email@gmail.com", "password"));

        DonorDTO returned = donorService.addDonor(donorDTO);
        assertEquals(donorDTO, returned);
    }
}