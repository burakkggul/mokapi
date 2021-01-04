package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.User;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private AddressResponse address;
    private String phone;
    private String website;
    private CompanyResponse company;

    public void setUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        if (user.getAddress() != null) {
            this.address = new AddressResponse(user.getAddress());
        } else {
            this.address = null;
        }
        this.phone = user.getPhone();
        this.website = user.getWebsite();
        if (user.getCompany() != null) {
            this.company = new CompanyResponse(user.getCompany());
        } else {
            this.company = null;
        }
    }
}
