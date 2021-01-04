package tr.com.burakgul.mokapi.controller;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.HttpStatus;
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
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
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
    public ResponseEntity<List<UserResponse>> getUserById() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<InfoResponse> addUsers(@RequestBody List<UserRequest> userRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUsers(userRequests));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoResponse> putUserById(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(id, userRequest));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<InfoResponse> patchUserById(@PathVariable("id") String id, @RequestBody JsonPatch jsonPatch) {
        return ResponseEntity.ok(userService.patchUserById(id, jsonPatch));
    }

    @DeleteMapping({"", "/{id}"})
    public ResponseEntity<InfoResponse> deleteUser(@PathVariable(required = false) String id) {
        if (id == null) {
            return ResponseEntity.ok(userService.deleteAllUsers());
        } else {
            return ResponseEntity.ok(userService.deleteUserById(id));
        }
    }
}
