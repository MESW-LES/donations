package les.donations.backendspring.service.address;

import les.donations.backendspring.dto.AddressDTO;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.address.IAddressMapper;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.model.Address;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.GeographicArea;
import les.donations.backendspring.repository.category.CategoryDao;
import les.donations.backendspring.repository.geographicArea.GeographicAreaDao;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AddressServiceTest {

    @Mock
    private IAddressMapper addressMapper;
    @Mock
    private GeographicAreaDao geographicAreaDao;
    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void AddAddressWithWrongPropertyInformationTest() throws NotFoundEntityException {
        AddressDTO addressDTO = new AddressDTO().street("street");
        when(addressMapper.dtoToModel(addressDTO)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addressService.addAddress(addressDTO));
    }

    @Test
    void AddAddressNotFoundTest() {
        AddressDTO addressDTO = new AddressDTO().street("street");
        Address address = new Address("street", 2L, "123456");
        when(addressMapper.dtoToModel(addressDTO)).thenReturn(address);
        when(geographicAreaDao.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundEntityException.class,
                () -> addressService.addAddress(addressDTO));
    }

    @Test
    void AddCorrectAddressTest() throws NotFoundEntityException {
        AddressDTO addressDTO = new AddressDTO().street("street");
        Address address = new Address("street", 2L, "123456");
        GeographicArea geographicArea = new GeographicArea("city", "parish", "test");
        geographicArea.setId(1L);
        addressDTO.geographicArea(1L);
        when(addressMapper.dtoToModel(addressDTO)).thenReturn(address);
        when(geographicAreaDao.findById(Mockito.any(Long.class))).
                thenReturn(Optional.of(geographicArea));

        Address returned = addressService.addAddress(addressDTO);
        Assertions.assertEquals(geographicArea, returned.getGeographicArea());
    }
}