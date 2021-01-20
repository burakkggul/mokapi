package tr.com.burakgul.mokapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.mokapi.annotation.AccessKeyNonNull;
import tr.com.burakgul.mokapi.dto.request.UserRequest;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
import tr.com.burakgul.mokapi.service.UserService;

import java.util.List;

/**
 * @author Burak GÜL
 * @since 1.0.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(@RequestHeader(value = "Access-Key", required = false) String accessKey) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(accessKey));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@RequestHeader(value = "Access-Key", required = false) String accessKey,
                                                    @PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(accessKey, id));
    }

    @PostMapping
    public ResponseEntity<InfoResponse> addUsers(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                                 @RequestBody List<UserRequest> userRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUsers(accessKey, userRequests));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoResponse> putUserById(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                                    @PathVariable("id") String id,
                                                    @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(accessKey, id, userRequest));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<InfoResponse> patchUserById(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                                      @PathVariable("id") String id,
                                                      @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        return ResponseEntity.ok(userService.patchUserById(accessKey, id, jsonPatch));
    }

    @DeleteMapping({"", "/{id}"})
    public ResponseEntity<InfoResponse> deleteUser(@RequestHeader(value = "Access-Key", required = false) @AccessKeyNonNull String accessKey,
                                                   @PathVariable(required = false) String id) {
        if (id == null) {
            return ResponseEntity.ok(userService.deleteAllUsers(accessKey));
        } else {
            return ResponseEntity.ok(userService.deleteUserById(accessKey, id));
        }
    }
}
