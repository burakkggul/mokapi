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
public class Company {
    @Id
    private String id;
    private String name;
    private String catchPhrase;
    private String bs;
}
