package tr.com.burakgul.mokapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {
    @Id
    private String id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
}
