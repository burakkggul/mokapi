package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressRequest {
    private String addressName;
    private String addressLine;
    private String country;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
    private Boolean active;
}
