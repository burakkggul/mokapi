package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Post {
    private String title;
    private String body;
    private List<Comment> comments;
}
