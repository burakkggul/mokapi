package tr.com.burakgul.mokapi.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.dto.request.UserRequest;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * It saves the users coming with user request to the database.
     *
     * @return @code InfoResponse
     */
    public InfoResponse saveUsers(List<UserRequest> userRequestList) {
        try {
            List<UserResponse> userResponseList = new ArrayList<>();
            userRequestList.forEach(r -> {
                User user = new User();
                user.setUserRequest(r);
                User savedUser = userRepository.save(user);
                UserResponse userResponse = new UserResponse();
                userResponse.setUser(savedUser);
                userResponseList.add(userResponse);
            });
            return new InfoResponse(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), userResponseList);
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService saveUsers error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Brings all users registered in the database in user response.
     *
     * @return @code List<UserResponse>
     */
    public List<UserResponse> getUsers() {
        try {
            List<User> userList = userRepository.findAll();
            List<UserResponse> userResponseList = new ArrayList<>();
            userList.forEach(u -> {
                UserResponse userResponse = new UserResponse();
                userResponse.setUser(u);
                userResponseList.add(userResponse);
            });
            if (userResponseList.size() == 0) {
                throw new NoSuchElementException("Users not found. You can use the post method to add users.");
            }
            return userResponseList;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService getUsers error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Returns the user response object with the id coming as a parameter in the user object.
     *
     * @param id @code String
     * @return @code UserResponse
     */
    public UserResponse getUserById(String id) {
        try {
            UserResponse userResponse = new UserResponse();
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                 userResponse.setUser(user.get());
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
            }

            return userResponse;
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService getUserById error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Updates the user according to the data received by id and user request and returns in user response.
     *
     * @param id @code String
     * @param userRequest @code UserRequest
     * @return @code InfoResponse<UserResponse>
     */
    public InfoResponse updateUserById(String id, UserRequest userRequest) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User user;
            if(userOptional.isPresent()){
                user = userOptional.get();
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
            }
            user.setUserRequest(userRequest);
            User savedUser = userRepository.save(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setUser(savedUser);
            return new InfoResponse(HttpStatus.OK, "Updated successfully.", userResponse);
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService updateUserById error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Deletes user with incoming user id.
     *
     * @param id @code String
     * @return @code InfoResponse
     */
    public InfoResponse deleteUserById(String id) {
        try {
            userRepository.deleteById(id);
            return new InfoResponse(HttpStatus.OK, "User " + id + " has been successfully deleted.");
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService deleteUserById error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Delete all users.
     *
     * @return @code InfoResponse
     */
    public InfoResponse deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new InfoResponse(HttpStatus.OK, "All users has been successfully deleted.");
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService deleteAllUsers error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * Updates user according to incoming id and json patch request
     *
     * @param id @code String
     * @param jsonPatch @code JsonPatch
     * @return @code InfoResponse<UserResponse>
     */
    public InfoResponse patchUserById(String id, JsonPatch jsonPatch) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<User> userOption = userRepository.findById(id);
            User user;
            if (userOption.isPresent()){
                user = userOption.get();
            }else{
                throw new NoSuchElementException("User not found. You can use the post method to add users.");
            }
            JsonNode patched = jsonPatch.apply(objectMapper.convertValue(user, JsonNode.class));
            user = objectMapper.treeToValue(patched, User.class);
            User patchedUser = userRepository.save(user);
            UserResponse patchedUserResponse = new UserResponse();
            patchedUserResponse.setUser(patchedUser);
            return new InfoResponse(HttpStatus.OK, "Patching was successful.", patchedUserResponse);
        }catch (JsonPatchException | JsonParseException | UnrecognizedPropertyException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": UserService patchUserById error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }
}
