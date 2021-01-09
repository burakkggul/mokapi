package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumRequest {
    private String title;
    private byte[] image;
    private byte[] thumbnail;
}
