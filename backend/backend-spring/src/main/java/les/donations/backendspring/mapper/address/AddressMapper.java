package les.donations.backendspring.mapper.address;

import les.donations.backendspring.dto.AddressDTO;
import les.donations.backendspring.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements IAddressMapper {
    @Override
    public Address dtoToModel(AddressDTO addressDTO) throws IllegalArgumentException {
        Address address = new Address(addressDTO.street, addressDTO.houseNumber, addressDTO.postalCode);
        return address;
    }

    @Override
    public AddressDTO modelToDTO(Address address) {
        return new AddressDTO().id(address.getId()).street(address.getStreet()).
                houseNumber(address.getHouseNumber()).postalCode(address.getPostalCode());
    }
}
