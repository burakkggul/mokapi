package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private AddressRequest address;
    private String phone;
    private String website;
    private CompanyRequest company;
}
