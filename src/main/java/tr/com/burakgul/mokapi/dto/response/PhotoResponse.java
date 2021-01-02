package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Photo;

@Getter
@Setter
public class PhotoResponse {
    private String albumId;
    private String title;
    private String imagePath;
    private String thumbnailPath;

    public PhotoResponse(Photo photo){
        this.albumId = photo.getAlbumId();
        this.title = photo.getTitle();
        this.imagePath = photo.getImagePath();
        this.thumbnailPath = photo.getThumbnailPath();
    }
}
