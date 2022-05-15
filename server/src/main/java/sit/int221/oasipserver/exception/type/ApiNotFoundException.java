package sit.int221.oasipserver.exception.type;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
//https://www.youtube.com/watch?v=PzK4ZXa2Tbc