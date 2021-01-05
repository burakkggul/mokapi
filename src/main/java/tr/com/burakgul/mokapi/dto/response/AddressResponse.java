package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Address;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {

    private String addressName;
    private String addressLine;
    private String country;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
    private Boolean active;

    public void setAddress(Address address) {
        this.addressName = address.getAddressName();
        this.country = address.getCountry();
        this.addressLine = address.getAddressLine();
        this.city = address.getCity();
        this.zipcode = address.getZipcode();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
        this.active = address.getActive();
    }
}


