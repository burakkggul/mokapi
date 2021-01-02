package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.burakgul.mokapi.dto.request.PhotoRequest;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Photo {
    @Id
    private String id;
    private String albumId;
    private String title;
    private String imagePath;
    private String thumbnailPath;

    public Photo(PhotoRequest photoRequest,String imagePath, String thumbnailPath){
        photoModifier(photoRequest,imagePath,thumbnailPath);
    }

    public Photo updatePhoto(PhotoRequest photoRequest, String imagePath, String thumbnailPath){
        photoModifier(photoRequest,imagePath,thumbnailPath);
        return this;
    }

    private void photoModifier(PhotoRequest photoRequest,String imagePath,String thumbnailPath){
        this.albumId = photoRequest.getAlbumId();
        this.title = photoRequest.getTitle();
        this.imagePath = imagePath;
        this.thumbnailPath = thumbnailPath;
    }
}
