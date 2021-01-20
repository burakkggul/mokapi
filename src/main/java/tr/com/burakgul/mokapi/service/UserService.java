package tr.com.burakgul.mokapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.annotation.DataExpireDateUpdate;
import tr.com.burakgul.mokapi.dto.request.UserRequest;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The class in which the data to be served in the "user controller" is prepared.
 *
 * @author Burak GUL
 * @since 1.0.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * It saves the users coming with user request to the database.
     * Access key-based records can be accessed from the database.
     * If sent null, data do not save.
     *
     * @param accessKey       The access key for database access
     * @param userRequestList User list to be saved.
     * @return infoResponse Model to be sent for information.
     */
    @Transactional
    @DataExpireDateUpdate
    public InfoResponse saveUsers(String accessKey, List<UserRequest> userRequestList) {
        List<User> accessibleUsers = userRepository.findByDataKeyContains(accessKey);
        if (accessibleUsers.size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Access-Key is invalid.");
        }
        List<User> userList = this.userRequestListConvertUserList(userRequestList, accessKey);
        List<User> savedUsers = userRepository.saveAll(userList);
        List<UserResponse> userResponseList = userListConvertUserResponseList(savedUsers);
        return new InfoResponse(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), userResponseList);
    }

    /**
     * Brings all users registered in the database in user response.
     * Access key-based records can be accessed from the database.
     * If sent null, read only data will be shown.
     *
     * @param accessKey The access key for database access
     * @return UserResponseList User information list.
     */
    @DataExpireDateUpdate
    public List<UserResponse> getUsers(String accessKey) {
        List<User> userList;
        if(accessKey == null){
            userList = userRepository.findByIsReadOnly(true);
        }else{
            userList = userRepository.findByDataKeyContains(accessKey);
        }
        if (userList.size() == 0) {
            throw new NoSuchElementException("Users not found. You can use the post method to add users.");
        }
        return this.userListConvertUserResponseList(userList);
    }

    /**
     * Returns the user response object with the id coming as a parameter in the user object.
     * Access key-based records can be accessed from the database.
     * If sent null, read only data will be shown.
     *
     * @param accessKey The access key for database access
     * @param id        a specific user identifier
     * @return UserResponse User information response.
     */
    @DataExpireDateUpdate
    public UserResponse getUserById(String accessKey, String id) {
        Optional<User> userOptional;
        if (accessKey == null) {
            userOptional = userRepository.findByIdAndIsReadOnly(id, true);
        } else {
            userOptional = userRepository.findByIdAndDataKeyContains(id, accessKey);
        }
        UserResponse userResponse = new UserResponse();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userResponse.setUser(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found. You can use the post method to add users.");
        }
        return userResponse;
    }

    /**
     * Updates the user according to the data received by id and user request and returns in user response.
     * Access key-based records can be accessed from the database.
     * If sent null, data do not change.
     *
     * @param accessKey   The access key for database access
     * @param id          a specific user identifier
     * @param userRequest The model containing the new information of the user to be updated
     * @return InfoResponse Model to be sent for information.
     */
    @Transactional
    @DataExpireDateUpdate
    public InfoResponse updateUserById(String accessKey, String id, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByIdAndDataKeyContains(id, accessKey);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }
        user.setUserRequest(userRequest);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(savedUser);
        return new InfoResponse(HttpStatus.OK, "Updated successfully.", userResponse);
    }

    /**
     * Deletes user with incoming user id.
     * Access key-based records can be accessed from the database.
     * If sent null, read only data will be shown.
     *
     * @param accessKey The access key for database access
     * @param id        String The access key for database access
     * @return InfoResponse Model to be sent for information.
     */
    @Transactional
    @DataExpireDateUpdate
    public InfoResponse deleteUserById(String accessKey, String id) {
        userRepository.deleteByIdAndDataKeyContains(id, accessKey);
        return new InfoResponse(HttpStatus.OK, "User " + id + " has been successfully deleted.");
    }

    /**
     * Delete all users.
     * Access key-based records can be accessed from the database.
     * If sent null, read only data will be shown.
     *
     * @params accessKey The access key for database access
     * @return InfoResponse Model to be sent for information.
     */
    @Transactional
    @DataExpireDateUpdate
    public InfoResponse deleteAllUsers(String accessKey) {
        userRepository.deleteAllByDataKeyContains(accessKey);
        return new InfoResponse(HttpStatus.OK, "All users has been successfully deleted.");
    }

    /**
     * Updates user according to incoming id and json patch request.
     * Access key-based records can be accessed from the database.
     * If sent null, read only data will be shown.
     *
     * @param accessKey the access key for database access
     * @param id        String The access key for database access
     * @param jsonPatch A JSONPatch document as defined by RFC 6902
     * @return infoResponse Model to be sent for information.
     */
    @Transactional
    @DataExpireDateUpdate
    public InfoResponse patchUserById(String accessKey, String id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        Optional<User> userOption = userRepository.findByIdAndDataKeyContains(id, accessKey);
        User user;
        if (userOption.isPresent()) {
            user = userOption.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found. You can use the post method to add users.");
        }
        user = this.jsonPatchToUser(jsonPatch, user);
        User patchedUser = userRepository.save(user);
        UserResponse patchedUserResponse = new UserResponse();
        patchedUserResponse.setUser(patchedUser);
        return new InfoResponse(HttpStatus.OK, "Patching was successful.", patchedUserResponse);
    }

    /**
     * It takes the convertable user list and converts it to a user information list.
     *
     * @param users user list to convertable.
     * @return userResponseList Converted user response list.
     */
    private List<UserResponse> userListConvertUserResponseList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setUser(user);
            userResponses.add(userResponse);
        });
        return userResponses;
    }

    /**
     *
     * @param userRequestList user request list to convertable.
     * @param accessKey access key to be added to user.
     * @return userList Converted user list.
     */
    private List<User> userRequestListConvertUserList(List<UserRequest> userRequestList, String accessKey) {
        List<User> convertedUserList = new ArrayList<>();
        userRequestList.forEach(r -> {
            User user = new User();
            user.setUserRequest(r);
            user.setIsReadOnly(false);
            user.setDataKey(accessKey);
            convertedUserList.add(user);
        });
        return convertedUserList;
    }

    /**
     * Patching user with json patch.
     *
     * @param jsonPatch A JSONPatch document as defined by RFC 6902
     * @param user user to patch.
     * @return User Patched user.
     * @throws JsonPatchException
     * @throws JsonProcessingException
     */
    private User jsonPatchToUser(JsonPatch jsonPatch, User user) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(user, JsonNode.class));
        user = objectMapper.treeToValue(patched, User.class);
        return user;
    }
}
