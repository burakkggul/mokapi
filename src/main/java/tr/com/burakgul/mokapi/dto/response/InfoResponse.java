package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class InfoResponse {
    private Date timestamp;
    private String status;
    private String message;

    /**
     * @param status
     * @param message
     */
    public InfoResponse(HttpStatus status, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status.value() + " " + status.getReasonPhrase();
        this.message = message;
    }

}
