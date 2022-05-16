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
        if(errorCode.matches("(.*)name empty;(.*)"))
            errorDetails.add(new ErrorDetail("bookingName", "must not be empty"));
        if(errorCode.matches("(.*)name null;(.*)"))
            errorDetails.add(new ErrorDetail("bookingName", "must not be null"));
        if(errorCode.matches("(.*)name notBlank;(.*)"))
            errorDetails.add(new ErrorDetail("bookingName", "must not be blank"));
        //Name size
        if(errorCode.matches("(.*)name length;(.*)"))
            errorDetails.add(new ErrorDetail("bookingName", "size must be between 1 and 100"));

        //Email valid
        if(errorCode.matches("(.*)email invalid;(.*)"))
            errorDetails.add(new ErrorDetail("bookingEmail", "must be a well-formed email address"));
        //Email Null
        if(errorCode.matches("(.*)email null;(.*)"))
            errorDetails.add(new ErrorDetail("bookingEmail", "must not be null"));
        if(errorCode.matches("(.*)email length;(.*)"))
            errorDetails.add(new ErrorDetail("bookingEmail", "size must be between 1 and 100"));

        //Note not empty
        if(errorCode.matches("(.*)note empty;(.*)"))
            errorDetails.add(new ErrorDetail("eventNotes", "must not be empty"));
        if(errorCode.matches("(.*)note notBlank;(.*)"))
            errorDetails.add(new ErrorDetail("eventNotes", "must not be empty"));
        //Note size
        if(errorCode.matches("(.*)note length;(.*)"))
            errorDetails.add(new ErrorDetail("eventNotes", "size must be between 0 and 500"));

        //eventCategory null
        if(errorCode.matches("(.*)eventCategoryId null;(.*)"))
            errorDetails.add(new ErrorDetail("eventCategoryId", "must not be null"));

        //eventStartTime null
        if(errorCode.matches("(.*)eventStartTime null;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "must not be null"));

        //validateDatetimeFuture
        if(errorCode.matches("(.*)future;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "must be a future date"));
        //overlap
        if(errorCode.matches("(.*)overlap;(.*)"))
            errorDetails.add(new ErrorDetail("eventStartTime", "overlapped with other events"));


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