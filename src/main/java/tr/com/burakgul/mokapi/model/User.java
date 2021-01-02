package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.burakgul.mokapi.dto.request.AddressRequest;
import tr.com.burakgul.mokapi.dto.request.CompanyRequest;
import tr.com.burakgul.mokapi.dto.request.UserRequest;

@Getter
@Setter
@NoArgsConstructor
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

    public User(UserRequest userRequest) {
        userModifier(userRequest);
    }

    public User updateUser(UserRequest userRequest){
        userModifier(userRequest);
        return this;
    }

    private void userModifier(UserRequest userRequest){
        this.name = userRequest.getName();
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        if (userRequest.getAddress() != null) {
            AddressRequest addressRequest = userRequest.getAddress();
            Address address = new Address(addressRequest.getStreet(),
                    addressRequest.getSuite(),
                    addressRequest.getCity(),
                    addressRequest.getZipcode(),
                    addressRequest.getLatitude(),
                    addressRequest.getLongitude());
            this.address = address;
        } else {
            this.address = null;
        }
        this.phone = userRequest.getPhone();
        this.website = userRequest.getWebsite();
        if (userRequest.getCompany() != null) {
            CompanyRequest companyRequest = userRequest.getCompany();
            Company company = new Company(companyRequest.getName(), companyRequest.getCatchPhrase(), companyRequest.getBs());
            this.company = company;
        } else {
            this.company = null;
        }
    }
}
