package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.mokapi.dto.request.AddressRequest;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String addressName;
    private String addressLine;
    private String country;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
    private Boolean active;

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressName = addressRequest.getAddressName();
        this.addressLine = addressRequest.getAddressLine();
        this.country = addressRequest.getCountry();
        this.city = addressRequest.getCity();
        this.zipcode = addressRequest.getZipcode();
        this.latitude = addressRequest.getLatitude();
        this.longitude = addressRequest.getLongitude();
        this.active = addressRequest.getActive();
    }
}
