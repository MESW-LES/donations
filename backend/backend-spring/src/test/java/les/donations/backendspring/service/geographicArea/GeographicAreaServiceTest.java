package les.donations.backendspring.service.geographicArea;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.mapper.geographicArea.IGeographicAreaMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.GeographicArea;
import les.donations.backendspring.repository.category.CategoryDao;
import les.donations.backendspring.repository.geographicArea.GeographicAreaDao;
import les.donations.backendspring.service.category.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class GeographicAreaServiceTest {

    @Mock
    private GeographicAreaDao geographicAreaDao;
    @Mock
    private IGeographicAreaMapper geographicAreaMapper;
    @InjectMocks
    private GeographicAreaService geographicAreaService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerGeographicAreaWithWrongInformationInformationTest() {
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO().city("city");
        when(geographicAreaMapper.dtoToModel(geographicAreaDTO)).thenThrow(
                new IllegalArgumentException("The city can't be null or empty"));

        try{
            geographicAreaService.registerGeographicArea(geographicAreaDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The city can't be null or empty", e.getMessage());
        }
    }

    @Test
    void registerCorrectGeographicArea() {
        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO().
                city("city").parish("parish").municipality("municipality");
        GeographicArea geographicArea = new GeographicArea("test", "test", "test");
        when(geographicAreaMapper.dtoToModel(geographicAreaDTO)).thenReturn(geographicArea);
        when(geographicAreaDao.saveAndFlush(geographicArea)).thenReturn(geographicArea);

        GeographicAreaDTO returned = geographicAreaService.registerGeographicArea(geographicAreaDTO);
        Assertions.assertEquals("city", returned.city);
        Assertions.assertEquals("parish", returned.parish);
        Assertions.assertEquals("municipality", returned.municipality);
    }

    @Test
    void getGeographicAreaWithIllegalInformation() {
        GeographicArea geographicArea = new GeographicArea("city", "parish", "test");
        List<GeographicArea> areas = Collections.singletonList(geographicArea);
        when(geographicAreaDao.findAll()).thenReturn(areas);
        when(geographicAreaMapper.modelToDTO(geographicArea)).thenThrow(
                new IllegalArgumentException("The municipality can't be null or empty"));

        try {
            PaginationDTO returned = geographicAreaService.getGeographicAreas();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("The municipality can't be null or empty", e.getMessage());
        }
    }

    @Test
    void getCorrectGeographicArea() {
        GeographicArea geographicArea = new GeographicArea("city", "parish", "test");
        GeographicAreaDTO areaDTO = new GeographicAreaDTO().city("city");
        List<GeographicArea> areas = Collections.singletonList(geographicArea);
        when(geographicAreaDao.findAll()).thenReturn(areas);
        when(geographicAreaMapper.modelToDTO(geographicArea)).thenReturn(areaDTO);

        PaginationDTO returned = geographicAreaService.getGeographicAreas();
        Assertions.assertEquals(1, returned.countResults);
        Assertions.assertEquals("city",
                ((GeographicAreaDTO)returned.results.get(0)).city);
    }

    @Test
    void getGeographicAreaModelNotFound() {
        when(geographicAreaDao.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        try {
            GeographicArea returned = geographicAreaService.getGeographicAreaModel(1L);
        } catch (NotFoundEntityException e) {
            Assertions.assertEquals("The geographic area with the id 1 does not exist!", e.getMessage());
        }
    }

    @Test
    void getCorrectGeographicAreaModel() throws NotFoundEntityException {
        GeographicArea geographicArea = new GeographicArea("city", "parish", "test");
        when(geographicAreaDao.findById(Mockito.any(Long.class))).thenReturn(Optional.of(geographicArea));
        GeographicArea returned = geographicAreaService.getGeographicAreaModel(1L);
        Assertions.assertEquals("city", returned.getCity());
        Assertions.assertEquals("parish", returned.getParish());
        Assertions.assertEquals("test", returned.getMunicipality());
    }
}