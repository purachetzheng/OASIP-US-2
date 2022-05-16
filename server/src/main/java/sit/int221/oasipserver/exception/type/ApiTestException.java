package sit.int221.oasipserver.exception.type;

public class ApiTestException extends RuntimeException{
    public ApiTestException(String message) {
        super(message);
    }

    public ApiTestException(String message, Throwable cause) {
        super(message, cause);
    }

}
//https://www.youtube.com/watch?v=PzK4ZXa2Tbc