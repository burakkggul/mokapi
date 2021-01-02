package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;

    public Address(String street, String suite, String city, String zipcode, String latitude, String longitude){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
