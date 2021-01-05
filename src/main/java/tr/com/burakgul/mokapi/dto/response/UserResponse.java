package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Address;
import tr.com.burakgul.mokapi.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private List<AddressResponse> addresses;
    private String phone;
    private String website;
    private CompanyResponse company;

    public void setUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        if (user.getAddresses() != null && user.getAddresses().size() > 0) {
            List<AddressResponse> addressResponseList = new ArrayList<>();
            for (Address address : user.getAddresses()) {
                AddressResponse addressResponse = new AddressResponse();
                addressResponse.setAddress(address);
                addressResponseList.add(addressResponse);
            }
            this.addresses = addressResponseList;
        } else {
            this.addresses = null;
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
