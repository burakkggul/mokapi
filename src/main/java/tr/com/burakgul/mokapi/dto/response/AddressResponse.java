package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Address;

@Getter
@Setter
public class AddressResponse {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;

    public AddressResponse(Address address) {
        this.street = address.getStreet();
        this.suite = address.getSuite();
        this.city = address.getCity();
        this.zipcode = address.getZipcode();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }
}


