package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Address;
import tr.com.burakgul.mokapi.model.Company;
import tr.com.burakgul.mokapi.model.Post;
import tr.com.burakgul.mokapi.model.Todo;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private List<Address> addresses;
    private String phone;
    private String website;
    private Company company;
    private List<Todo> todos;
    private List<Post> posts;
}
