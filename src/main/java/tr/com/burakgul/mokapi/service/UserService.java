package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Service
public class UserService {

    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * TODO unit test will be written
     * @param userRequestList
     * @return
     */
    public ResponseEntity saveUsers(List<UserRequest> userRequestList) {
        try {
            userRequestList.stream().forEach(r -> userRepository.save(new User(r)));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new InfoResponse(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            logger.error(e.getMessage()+": "+e.getClass().getCanonicalName()+": UserService saveUsers error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * TODO unit test will be written
     * @return
     */
    public ResponseEntity getUsers() {
        try {
            List<User> userList = userRepository.findAll();
            List<UserResponse> userResponseList = new ArrayList<>();
            userList.stream().forEach(u -> userResponseList.add(new UserResponse(u)));
            return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage()+": "+e.getClass().getCanonicalName()+": UserService getUsers error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    /**
     * TODO unit test will be written
     * @param id
     * @param userRequest
     * @return
     */
    public ResponseEntity updateUserById(String id,UserRequest userRequest) {
        try {
            User updatedUser = userRepository.findById(id).get().updateUser(userRequest);
            userRepository.save(updatedUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new InfoResponse(HttpStatus.OK, "Updated successfully."));
        }catch (Exception e){
            logger.error(e.getMessage()+": "+e.getClass().getCanonicalName()+": UserService updateUserById error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }
}
