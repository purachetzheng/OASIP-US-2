package sit.int221.oasipserver.exception;

public class TestException extends RuntimeException{
    public TestException(String message){
        super("Could not find employee " + message);
    }
}
