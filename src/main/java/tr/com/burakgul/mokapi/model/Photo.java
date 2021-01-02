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
public class Photo {
    @Id
    private String id;
    private String albumId;
    private String title;
    private String imagePath;
    private String thumbnailPath;
}
