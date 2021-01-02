package tr.com.burakgul.mokapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.mokapi.dto.request.UserRequest;
import tr.com.burakgul.mokapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addUsers(@RequestBody List<UserRequest> userRequests) {
        return userService.saveUsers(userRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity putUserById(@PathVariable("id") String id,
                                      @RequestBody UserRequest userRequest) {
        return userService.updateUserById(id,userRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchUserById(@PathVariable("id") String id,
                                        @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") String id,
                                         @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().build();
    }

}
