package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Address;
import tr.com.burakgul.mokapi.model.Company;
import tr.com.burakgul.mokapi.model.Post;
import tr.com.burakgul.mokapi.model.Todo;
import tr.com.burakgul.mokapi.model.User;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private List<Address> addresses;
    private String phone;
    private String website;
    private Company company;
    private List<Todo> todos;
    private List<Post> posts;

    public void setUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.addresses = user.getAddresses();
        this.phone = user.getPhone();
        this.website = user.getWebsite();
        this.company = user.getCompany();
        this.todos = user.getTodos();
        this.posts = user.getPosts();
    }
}
