package tr.com.burakgul.mokapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class InfoResponse {
    private final Date timestamp;
    private final Integer status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public InfoResponse(HttpStatus status, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status.value();
        this.message = message;
    }

    public InfoResponse(HttpStatus status, String message, Object data) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

}
