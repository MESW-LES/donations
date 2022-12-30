package les.donations.backendspring.service.user;

import les.donations.backendspring.dto.UserDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.repository.administrator.AdministratorDao;
import les.donations.backendspring.service.company.ICompanyService;
import les.donations.backendspring.service.person.IPersonService;
import les.donations.backendspring.util.RoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IPersonService personService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public UserDTO login(UserDTO userDTO) throws NotFoundEntityException {

        // checks if is donor
        if(userDTO.email != null && personService.existsPersonByEmail(userDTO.email)){
            return new UserDTO().role(RoleConstant.DONOR);
        }

        // checks if is donne
        if(userDTO.email != null && companyService.existsCompanyByEmail(userDTO.email)){
            return new UserDTO().role(RoleConstant.DONNE);
        }

        // checks if is an admin
        if(userDTO.email != null && administratorDao.existsAdministratorByEmailAndActive(userDTO.email, true)){
            return new UserDTO().role(RoleConstant.ADMIN);
        }

        throw new NotFoundEntityException("No user exists with the provided email!");
    }
}
