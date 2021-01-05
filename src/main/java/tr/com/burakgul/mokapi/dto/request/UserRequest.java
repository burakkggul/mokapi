package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private List<AddressRequest> addresses;
    private String phone;
    private String website;
    private CompanyRequest company;
}
