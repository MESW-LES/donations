package les.donations.backendspring.controller.demo;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DemoDTO;
import les.donations.backendspring.service.demo.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController implements IDemoController {

    @Autowired
    private IDemoService demoService;

    @Override
    public ResponseEntity<ApiReturnMessage> getDemo() {
        final DemoDTO demoDTO = demoService.getDemo();
        final ApiReturnMessage apiReturnMessage = new ApiReturnMessage(200, demoDTO);
        return new ResponseEntity<>(apiReturnMessage, HttpStatus.OK);
    }
}
