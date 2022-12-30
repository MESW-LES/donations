package les.donations.backendspring.controller.geographicArea;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.category.CategoryController;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.service.geographicArea.IGeographicAreaService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class GeographicAreaControllerTest {
    @Mock
    private IGeographicAreaService geographicAreaService;
    @InjectMocks
    private GeographicAreaController geographicAreaController;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("provideMessageExceptions")
    void registerGeographicAreaIncorrectInformation(String message) {
        GeographicAreaDTO areaDTO = new GeographicAreaDTO().city("city");
        when(geographicAreaService.registerGeographicArea(areaDTO))
                .thenThrow(new IllegalArgumentException(message));
        ResponseEntity<ApiReturnMessage> result = geographicAreaController.registerGeographicArea(areaDTO);
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void registerCorrectGeographicArea() {
        GeographicAreaDTO areaDTO = new GeographicAreaDTO().city("city");
        when(geographicAreaService.registerGeographicArea(areaDTO))
                .thenReturn(areaDTO);
        ResponseEntity<ApiReturnMessage> result = geographicAreaController.registerGeographicArea(areaDTO);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(areaDTO, result.getBody().getMessage());
    }

    @Test
    void getCorrectGeographicArea() {
        GeographicAreaDTO areaDTO = new GeographicAreaDTO().city("city");
        PaginationDTO paginationDTO = new PaginationDTO().countResults(1).
                results(Collections.singletonList(areaDTO));
        when(geographicAreaService.getGeographicAreas())
                .thenReturn(paginationDTO);
        ResponseEntity<ApiReturnMessage> result = geographicAreaController.getGeographicAreas();
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(paginationDTO, result.getBody().getMessage());
    }

    private static Stream<Arguments> provideMessageExceptions(){
        return Stream.of(Arguments.of("The city can't be null or empty"),
                Arguments.of("The parish can't be null or empty"),
                Arguments.of("The municipality can't be null or empty"));
    }

}