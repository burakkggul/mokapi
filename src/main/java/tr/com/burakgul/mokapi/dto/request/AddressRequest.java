package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressRequest {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
}
