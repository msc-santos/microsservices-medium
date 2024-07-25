package ms.dev.request.infra;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    private HttpStatus httpStatus;
    private String message;

    public RestErrorMessage() { }

    public RestErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
