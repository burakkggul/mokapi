package tr.com.burakgul.mokapi.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class PhotoRequest {
    private String albumId;
    private String title;
    private MultipartFile image;
    private MultipartFile thumbnail;
}
