package tr.com.burakgul.mokapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.mokapi.dto.response.AccessKeyResponse;
import tr.com.burakgul.mokapi.service.AccessHeaderService;

@RestController
@RequestMapping("access-header")
public class AccessHeaderController {

    private final AccessHeaderService accessHeaderService;

    public AccessHeaderController(AccessHeaderService accessHeaderService) {
        this.accessHeaderService = accessHeaderService;
    }

    @GetMapping
    public ResponseEntity<AccessKeyResponse> createAccessKeyAndWritableData(){
        AccessKeyResponse accessKeyResponse = accessHeaderService.getAccessHeader();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Access-Key",accessKeyResponse.getAccessKey())
                .body(accessKeyResponse);
    }

}
