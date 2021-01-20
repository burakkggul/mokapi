package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class Address {
    @Field("address_name")
    @Indexed(unique = true)
    private String addressName;
    @Field("address_line")
    private String addressLine;
    private String country;
    private String city;
    private String zipcode;
    private Geolocation geolocation;
    @Field("communication_preference")
    private CommunicationPreference communicationPreference;
    private Boolean active;
}
