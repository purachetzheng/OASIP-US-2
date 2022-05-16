package sit.int221.oasipserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sit.int221.oasipserver.exception.type.ApiNotFoundException;
import sit.int221.oasipserver.exception.type.ApiRequestException;
import sit.int221.oasipserver.exception.type.ApiTestException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
//                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e){
        // 1. Create payload containing exception details

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
//                e,
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {ApiTestException.class})
    public ResponseEntity<Object> handleApiTestException(ApiTestException e){
        // 1. Create payload containing exception details
        List<ErrorDetail> errorDetails = new ArrayList<>();
        String errorCode = e.getMessage();
        //Name Null or empty
        if(errorCode.matches("(.*)name empty/null;(.*)"))
            errorDetails.add(new ErrorDetail("name", "input not be empty/null."));
        //Name size
        if(errorCode.matches("(.*)name length;(.*)"))
            errorDetails.add(new ErrorDetail("name", "the length exceeded the size."));

        //Email valid
        if(errorCode.matches("(.*)email invalid;(.*)"))
            errorDetails.add(new ErrorDetail("email", "bookingEmail is invalid."));
        //Email Null
        if(errorCode.matches("(.*)email null;(.*)"))
            errorDetails.add(new ErrorDetail("email", "input not be null."));

        //Note not empty
        if(errorCode.matches("(.*)note empty;(.*)"))
            errorDetails.add(new ErrorDetail("note", "input not be empty/null."));
        //Note size
        if(errorCode.matches("(.*)note length;(.*)"))
            errorDetails.add(new ErrorDetail("name", "the length exceeded the size."));

        //eventCategory null
        if(errorCode.matches("(.*)eventCategory null;(.*)"))
            errorDetails.add(new ErrorDetail("eventCategory", "input not be null."));

        //eventStartTime null
        if(errorCode.matches("(.*)eventStartTime null;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "input not be null."));
        //validateDatetimeFuture
        if(errorCode.matches("(.*)future;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "eventStartTime is NOT in the future."));
        //overlap
        if(errorCode.matches("(.*)overlap;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "the eventStartTime is overlapped."));


        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiReqException apiReqException = new ApiReqException(
                "Event attributes validation failed!",
//                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                errorDetails
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiReqException, badRequest);
    }
}
//https://www.youtube.com/watch?v=PzK4ZXa2Tbc