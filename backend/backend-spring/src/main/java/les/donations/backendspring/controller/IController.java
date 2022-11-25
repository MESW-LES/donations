package les.donations.backendspring.controller;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.ModelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IController {

    public ResponseEntity<ApiReturnMessage> ok(ModelDTO message){
        return new ResponseEntity<>(new ApiReturnMessage(HttpStatus.OK.value(), message), HttpStatus.OK);
    }

    public ResponseEntity<ApiReturnMessage> created(ModelDTO message){
        return new ResponseEntity<>(new ApiReturnMessage(HttpStatus.CREATED.value(), message), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiReturnMessage> badRequest(String errorMessage){
        return new ResponseEntity<>(new ApiReturnMessage(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiReturnMessage> notFound(String errorMessage){
        return new ResponseEntity<>(new ApiReturnMessage(HttpStatus.NOT_FOUND.value(), errorMessage), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ApiReturnMessage> internalServerError(String errorMessage){
        return new ResponseEntity<>(new ApiReturnMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
