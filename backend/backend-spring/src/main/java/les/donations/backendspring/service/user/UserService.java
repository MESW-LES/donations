package les.donations.backendspring.service.user;

import les.donations.backendspring.dto.UserDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.company.ICompanyService;
import les.donations.backendspring.service.person.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IPersonService personService;
    @Autowired
    private ICompanyService companyService;

    @Override
    public UserDTO login(UserDTO userDTO) throws NotFoundEntityException {

        // checks if is donor
        if(userDTO.email != null && personService.existsPersonByEmail(userDTO.email)){
            return new UserDTO().role("donor");
        }

        // checks if is donne
        if(userDTO.email != null && companyService.existsCompanyByEmail(userDTO.email)){
            return new UserDTO().role("donne");
        }

        // TODO: checks if is admin

        throw new NotFoundEntityException("No user exists with the provided email!");
    }
}
