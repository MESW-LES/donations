package les.donations.backendspring.controller.demo;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IDemoController {

    @GetMapping("/demos")
    ResponseEntity<ApiReturnMessage> getDemo();

}
