package les.donations.backendspring.service.user;

import les.donations.backendspring.dto.UserDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;

public interface IUserService {

    /**
     * Method that checks if the user can be logged in and retrieves information about his role
     * @param userDTO information about the user to be logged in
     * @return information about the user's role
     * @throws NotFoundEntityException if no user exists with the provided email
     */
    UserDTO login(UserDTO userDTO) throws NotFoundEntityException;
}
