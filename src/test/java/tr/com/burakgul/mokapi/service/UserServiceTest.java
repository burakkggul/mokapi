package tr.com.burakgul.mokapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tr.com.burakgul.mokapi.dto.request.UserRequest;
import tr.com.burakgul.mokapi.dto.response.InfoResponse;
import tr.com.burakgul.mokapi.dto.response.UserResponse;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testSaveUsers() {
        List<UserRequest> userRequests = Arrays.asList(Mockito.mock(UserRequest.class),Mockito.mock(UserRequest.class));
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getName()).thenReturn("Test0");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userMock);
        InfoResponse infoResponse = userService.saveUsers(userRequests);
        Assertions.assertEquals(201,infoResponse.getStatus());
        Assertions.assertEquals("Test0",((UserResponse)((List)infoResponse.getData()).get(0)).getName());
    }

    @Test
    void testGetUsers() {
        List<User> userMockList = Arrays.asList(Mockito.mock(User.class),Mockito.mock(User.class));
        Mockito.when(userMockList.get(0).getName()).thenReturn("Test");
        Mockito.when(userRepository.findAll()).thenReturn(userMockList);
        List<UserResponse> userResponseList = userService.getUsers();
        Assertions.assertEquals("Test",userResponseList.get(0).getName());
    }

    @Test
    void testGetUserById() {
        List<User> userMockList = Arrays.asList(Mockito.mock(User.class),Mockito.mock(User.class));
        Mockito.when(userMockList.get(0).getName()).thenReturn("Test0");
        Mockito.when(userRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMockList.get(0)));
        UserResponse userResponse = userService.getUserById("Test");
        Assertions.assertEquals("Test0",userResponse.getName());
    }

    @Test
    void testUpdateUserById() {
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getName()).thenReturn("UpdatedTestName");
        Mockito.when(userRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMock));
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userMock);
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        InfoResponse infoResponse = userService.updateUserById("",userRequest);
        Assertions.assertEquals("UpdatedTestName",((UserResponse)infoResponse.getData()).getName());
    }

    @Test
    void testPatchUserById() throws IOException {
        String json = "[{\"op\":\"remove\",\"path\":\"/name\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonPatch jsonPatch = JsonPatch.fromJson(jsonNode);
        User userMock = new User();
        userMock.setName("Test");
        User savedUserMock = new User();
        savedUserMock.setName(null);
        savedUserMock.setUsername("UserTest");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(savedUserMock);
        Mockito.when(userRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMock));
        InfoResponse infoResponse = userService.patchUserById("",jsonPatch);
        Assertions.assertEquals(200,infoResponse.getStatus());
        Assertions.assertNull(((UserResponse)infoResponse.getData()).getName());
        Assertions.assertEquals("UserTest",((UserResponse)infoResponse.getData()).getUsername());
    }

    @Test
    void testDeleteUserById() {
        InfoResponse infoResponse = userService.deleteUserById("");
        Assertions.assertEquals(200,infoResponse.getStatus());
    }

    @Test
    void testDeleteAllUsers() {
        InfoResponse infoResponse = userService.deleteUserById("");
        Assertions.assertEquals(200,infoResponse.getStatus());
    }
}
