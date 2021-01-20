package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AccessKeyResponse extends InfoResponse{
    private String accessKey;

    public AccessKeyResponse(HttpStatus status, String message, Object data, String accessKey) {
        super(status, message, data);
        this.accessKey = accessKey;
    }
}
