package les.donations.backendspring.controller.user;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {

    /**
     * Method that checks if the user can be logged in and retrieves information about his role
     * @param userDTO information about the user to be logged in
     * @return information about the user's role
     */
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> login(@RequestBody UserDTO userDTO);
}
