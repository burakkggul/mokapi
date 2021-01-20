package tr.com.burakgul.mokapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
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
    void whenAccessKeyTrueThenDoesSaveUsers() {
        List<UserRequest> userRequests = Arrays.asList(Mockito.mock(UserRequest.class),Mockito.mock(UserRequest.class));
        List<User> userMockList = Arrays.asList(Mockito.mock(User.class),Mockito.mock(User.class));
        Mockito.when(userMockList.get(0).getName()).thenReturn("Test0");
        Mockito.when(userRepository.saveAll(ArgumentMatchers.anyList())).thenReturn(userMockList);
        Mockito.when(userRepository.findByDataKeyContains(ArgumentMatchers.any(String.class))).thenReturn(userMockList);
        InfoResponse infoResponse = userService.saveUsers("",userRequests);
        Assertions.assertEquals(201,infoResponse.getStatus());
        Assertions.assertEquals("Test0",((UserResponse)((List)infoResponse.getData()).get(0)).getName());
    }

    @Test
    void whenAccessKeyNullThenDoesNotSaveUser() {
        List<UserRequest> userRequests = Arrays.asList(Mockito.mock(UserRequest.class),Mockito.mock(UserRequest.class));
        Assertions.assertThrows(ResponseStatusException.class,() -> userService.saveUsers(null,userRequests));
    }

    @Test
    void testGetUsers() {
        List<User> userMockList = Arrays.asList(Mockito.mock(User.class),Mockito.mock(User.class));
        Mockito.when(userMockList.get(0).getName()).thenReturn("Test");
        Mockito.when(userRepository.findByDataKeyContains(ArgumentMatchers.any(String.class))).thenReturn(userMockList);
        List<UserResponse> userResponseWithAccessKey = userService.getUsers("");
        Assertions.assertEquals("Test",userResponseWithAccessKey.get(0).getName());
    }

    @Test
    void testGetUserById() {
        List<User> userMockList = Arrays.asList(Mockito.mock(User.class),Mockito.mock(User.class));
        Mockito.when(userMockList.get(0).getName()).thenReturn("Test0");
        Mockito.when(userRepository.findByIdAndDataKeyContains(ArgumentMatchers.any(String.class),ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMockList.get(0)));
        UserResponse userResponse = userService.getUserById("","Test");
        Assertions.assertEquals("Test0",userResponse.getName());
    }

    @Test
    void whenAccessKeyTrueThenDoesUpdateUser() {
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getName()).thenReturn("UpdatedTestName");
        Mockito.when(userRepository.findByIdAndDataKeyContains(ArgumentMatchers.any(String.class),ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMock));
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userMock);
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        InfoResponse infoResponse = userService.updateUserById("","",userRequest);
        Assertions.assertEquals("UpdatedTestName",((UserResponse)infoResponse.getData()).getName());
    }

    @Test
    void whenAccessKeyNullThenDoesNotUserUpdate() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        Assertions.assertThrows(ResponseStatusException.class,() -> userService.updateUserById(null,"",userRequest));
    }

    @Test
    void testPatchUserById() throws IOException, JsonPatchException {
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
        Mockito.when(userRepository.findByIdAndDataKeyContains(ArgumentMatchers.any(String.class),ArgumentMatchers.any(String.class))).thenReturn(Optional.of(userMock));
        InfoResponse infoResponse = userService.patchUserById("","",jsonPatch);
        Assertions.assertEquals(200,infoResponse.getStatus());
        Assertions.assertNull(((UserResponse)infoResponse.getData()).getName());
        Assertions.assertEquals("UserTest",((UserResponse)infoResponse.getData()).getUsername());
    }

    @Test
    void testDeleteUserById() {
        InfoResponse infoResponse = userService.deleteUserById("","");
        Assertions.assertEquals(200,infoResponse.getStatus());
    }

    @Test
    void testDeleteAllUsers() {
        InfoResponse infoResponse = userService.deleteAllUsers("");
        Assertions.assertEquals(200,infoResponse.getStatus());
    }
}
