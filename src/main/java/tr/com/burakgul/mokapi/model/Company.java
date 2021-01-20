package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private String name;
    @Field("catch_phrase")
    private String catchPhrase;
}
