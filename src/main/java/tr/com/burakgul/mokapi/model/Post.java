package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class Post {
    private String id;
    private String title;
    private String body;
    private List<Comment> comments;

    public Post() {
        this.id = new ObjectId().toString();
    }
}
