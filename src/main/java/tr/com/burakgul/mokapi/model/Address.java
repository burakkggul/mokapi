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
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressName = addressRequest.getAddressName();
        this.street = addressRequest.getStreet();
        this.suite = addressRequest.getSuite();
        this.city = addressRequest.getCity();
        this.zipcode = addressRequest.getZipcode();
        this.latitude = addressRequest.getLatitude();
        this.longitude = addressRequest.getLongitude();
    }
}
