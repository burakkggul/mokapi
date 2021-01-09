package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.burakgul.mokapi.dto.request.UserRequest;

import java.util.List;

@Getter
@Setter
@Document("users")
public class User extends BaseModel{
    @Id
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
    private List<Album> albums;

    public void setUserRequest(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        this.addresses = userRequest.getAddresses();
        this.phone = userRequest.getPhone();
        this.website = userRequest.getWebsite();
        this.company = userRequest.getCompany();
        this.todos = userRequest.getTodos();
        this.posts = userRequest.getPosts();
        this.albums = userRequest.getAlbums();
    }
}
