package les.donations.backendspring.service.address;

import les.donations.backendspring.dto.AddressDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.address.IAddressMapper;
import les.donations.backendspring.model.Address;
import les.donations.backendspring.model.GeographicArea;
import les.donations.backendspring.repository.geographicArea.GeographicAreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private GeographicAreaDao geographicAreaDao;

    @Override
    public Address addAddress(AddressDTO addressDTO) throws IllegalArgumentException, NotFoundEntityException {
        Address address = addressMapper.dtoToModel(addressDTO);
        Long geographicAreaId = addressDTO.geographicArea;
        Optional<GeographicArea> geographicAreaOptional = geographicAreaDao.findById(geographicAreaId);
        if (!geographicAreaOptional.isPresent()) throw new NotFoundEntityException("Geographic area with this id doesn't exist");
        GeographicArea geographicArea = geographicAreaOptional.get();
        address.setGeographicArea(geographicArea);
        return address;
    }

}
