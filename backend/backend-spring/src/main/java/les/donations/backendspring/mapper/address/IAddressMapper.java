package les.donations.backendspring.mapper.address;

import les.donations.backendspring.dto.AddressDTO;
import les.donations.backendspring.model.Address;

public interface IAddressMapper {
    Address dtoToModel(final AddressDTO addressDTO) throws IllegalArgumentException;
    AddressDTO modelToDTO(final Address address);
}
