package sit.int221.oasipserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

}
//https://www.youtube.com/watch?v=PzK4ZXa2Tbc