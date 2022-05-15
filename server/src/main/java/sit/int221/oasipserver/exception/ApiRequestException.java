package sit.int221.oasipserver.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }


}
//https://www.youtube.com/watch?v=PzK4ZXa2Tbc