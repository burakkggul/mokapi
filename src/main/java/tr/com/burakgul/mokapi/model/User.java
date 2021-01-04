package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.burakgul.mokapi.dto.request.UserRequest;

@Getter
@Setter
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public void setUserRequest(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        if (userRequest.getAddress() != null) {
            this.address = new Address(userRequest.getAddress());
        } else {
            this.address = null;
        }
        this.phone = userRequest.getPhone();
        this.website = userRequest.getWebsite();
        if (userRequest.getCompany() != null) {
            this.company = new Company(userRequest.getCompany());
        } else {
            this.company = null;
        }
    }
}
