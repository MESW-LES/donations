package les.donations.backendspring.controller.user;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.UserDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController extends IController implements IUserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ApiReturnMessage> login(UserDTO userDTO) {
        try{
            // if the user exists then its logged in and his role is retrieved
            userDTO = userService.login(userDTO);
            return ok(userDTO);

            // if the user does not exist
        }catch (NotFoundEntityException e){
            return notFound(e.getMessage());
        }
    }
}
