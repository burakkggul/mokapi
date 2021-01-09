package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class Album{
    private String title;
    @Field("image_path")
    private String imagePath;
    @Field("thumbnail_path")
    private String thumbnailPath;
}
