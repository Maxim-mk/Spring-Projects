package maxim.spring.restapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorErrorResponse {
    private String message;
    private String timestamp;

    public SensorErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
