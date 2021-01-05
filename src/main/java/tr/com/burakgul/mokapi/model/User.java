package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.burakgul.mokapi.dto.request.AddressRequest;
import tr.com.burakgul.mokapi.dto.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private List<Address> addresses;
    private String phone;
    private String website;
    private Company company;

    public void setUserRequest(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        if (userRequest.getAddresses() != null && userRequest.getAddresses().size() > 0) {
            List<Address> addressList = new ArrayList<>();
            for (AddressRequest addressRequest : userRequest.getAddresses()) {
                Address address = new Address();
                address.setAddressRequest(addressRequest);
                addressList.add(address);
            }
            this.addresses = addressList;
        } else {
            this.addresses = null;
        }
        this.phone = userRequest.getPhone();
        this.website = userRequest.getWebsite();
        if (userRequest.getCompany() != null) {
            Company company = new Company();
            company.setCompanyRequest(userRequest.getCompany());
            this.company = company;
        } else {
            this.company = null;
        }
    }
}
