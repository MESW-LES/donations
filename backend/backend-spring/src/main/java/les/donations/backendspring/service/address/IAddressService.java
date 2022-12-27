package les.donations.backendspring.service.address;

import les.donations.backendspring.dto.AddressDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.Address;

public interface IAddressService {
    Address addAddress(AddressDTO addressDTO) throws IllegalArgumentException, NotFoundEntityException;
}
