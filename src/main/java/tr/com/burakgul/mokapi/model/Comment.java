package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Comment {
    private String id;
    private String name;
    private String email;
    private String body;

    public Comment() {
        this.id = new ObjectId().toString();
    }
}
