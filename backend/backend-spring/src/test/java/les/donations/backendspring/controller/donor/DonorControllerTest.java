package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.dto.PersonDTO;
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

import java.io.IOException;
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
    void addDonorWithIllegalInformation(String message) throws IOException {
        DonorDTO donorDTO = new DonorDTO();
        // mock the service
        when(donorService.registerDonor(donorDTO)).thenThrow(new IllegalArgumentException(message));
        // call the controller
        ResponseEntity<ApiReturnMessage> result = donorController.registerDonor(donorDTO);
        Assertions.assertEquals(400, result.getStatusCodeValue());
        Assertions.assertEquals(message, result.getBody().getErrorMessage());
    }

    @Test
    void addCategoryWithValidInformation() throws IOException {
        PersonDTO personDTO = new PersonDTO().id(Long.valueOf("1")).firstName("Anna").lastName("Smith")
                .nif("023456789").address("address").email("person.test@gmail.com").password("password");
        DonorDTO donorDTO = new DonorDTO().id(Long.valueOf("2")).person(personDTO);
        // mock the service
        when(donorService.registerDonor(donorDTO)).thenReturn(donorDTO);
        // call the controller
        ResponseEntity<ApiReturnMessage> result = donorController.registerDonor(donorDTO);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(donorDTO, result.getBody().getMessage());
    }

    private static Stream<Arguments> provideMessageExceptions(){
        return Stream.of(Arguments.of("NIF or email already exists"));
    }

}
