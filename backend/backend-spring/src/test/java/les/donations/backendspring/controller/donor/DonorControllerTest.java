package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.donor.DonorController;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.service.donor.IDonorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DonorControllerTest {

    @Mock
    private IDonorService donorService;
    @InjectMocks
    private DonorController donorController;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("provideMessageExceptions")
    void addDonorWithIllegalInformation(String message){
        DonorDTO donorDTO = new DonorDTO();
        // mock the service
        when(donorService.addDonor(donorDTO)).thenThrow(new IllegalArgumentException(message));
        // call the controller
        ResponseEntity<ApiReturnMessage> result = donorController.registerDonor(donorDTO);
        Assertions.assertEquals(400, result.getStatusCodeValue());
        Assertions.assertEquals(message, result.getBody().getMessage().toString());
    }


    @Test
    void addDonorWithValidInformation(){
        DonorDTO donorDTO = new DonorDTO().firstName("Maria").lastName("Ivanova").nif("123456789").address("Downing street 10")
                .email("maria.ivanova@gmail.com").password("password");
        // mock the service
        when(donorService.addDonor(donorDTO)).thenReturn(donorDTO);
        // call the controller
        ResponseEntity<ApiReturnMessage> result = donorController.registerDonor(donorDTO);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(donorDTO, result.getBody().getMessage());
    }

    private static Stream<Arguments> provideMessageExceptions(){
        return Stream.of(Arguments.of("NIF or email already exists"));
    }

}
