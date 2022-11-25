package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.donee.IDoneeService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DoneeControllerTest {

    @Mock
    private IDoneeService doneeService;
    @InjectMocks
    private DoneeController doneeController;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("unchecked")
    @ParameterizedTest
    @MethodSource("provideExceptions")
    void registerDoneeButWithExceptionsTest(Integer statusCode, Class exceptionClass) throws IOException, NotFoundEntityException {
        // prepares the data
        DoneeDTO doneeDTO = new DoneeDTO();
        // mocks the service
        when(doneeService.registerDonee(doneeDTO)).thenThrow(exceptionClass);
        // calls the method
        ResponseEntity<ApiReturnMessage> response = doneeController.registerDonee(doneeDTO);
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Test
    void registerDoneeWithoutAnyExceptionTest() throws IOException, NotFoundEntityException {
        // prepares the data
        DoneeDTO doneeDTO = new DoneeDTO();
        // mocks the service
        when(doneeService.registerDonee(doneeDTO)).thenReturn(doneeDTO);
        // calls the method
        ResponseEntity<ApiReturnMessage> response = doneeController.registerDonee(doneeDTO);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(doneeDTO, response.getBody().getMessage());
    }

    private static Stream<Arguments> provideExceptions(){
        return Stream.of(Arguments.of(400, IllegalArgumentException.class), Arguments.of(500, IOException.class),
                Arguments.of(404, NotFoundEntityException.class));
    }

}