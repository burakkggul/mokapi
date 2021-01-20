package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    @Id
    private String id;
    @Field("expire_date")
    private Date expireDate;
    @Field("data_key")
    private String dataKey;
    @Field("is_read_only")
    @Indexed(unique = true)
    private Boolean isReadOnly;
}
