package tr.com.burakgul.mokapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.dto.response.AccessKeyResponse;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.util.MockDataInitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccessHeaderService {

    private final MockDataInitUtil mockDataInitUtil;

    private final Logger logger = LoggerFactory.getLogger(AccessHeaderService.class);

    public AccessHeaderService(MockDataInitUtil mockDataInitUtil) {
        this.mockDataInitUtil = mockDataInitUtil;
    }

    public AccessKeyResponse getAccessHeader(){
        try{
            String accessKey = generateRandomAccessKey();
            List<User> users = mockDataInitUtil.userDocumentsInitByFile(accessKey);
            List<UserResponse> userResponses = userListConvertUserResponseList(users);
            return new AccessKeyResponse(HttpStatus.CREATED,"Access key and writable data created.",userResponses,accessKey);
        }catch (Exception e){
            logger.error(e.getMessage() + ": " + e.getClass().getCanonicalName() + ": AccessHeaderService getAccessHeader error.");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred. We took note to fix it as soon as possible.");
        }
    }

    private List<UserResponse> userListConvertUserResponseList(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setUser(user);
            userResponses.add(userResponse);
        });
        return userResponses;
    }

    private String generateRandomAccessKey() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
