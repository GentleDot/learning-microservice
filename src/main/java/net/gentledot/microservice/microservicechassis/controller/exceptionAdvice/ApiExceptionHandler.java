package net.gentledot.microservice.microservicechassis.controller.exceptionAdvice;

import net.gentledot.microservice.microservicechassis.exception.BadInputRequestException;
import net.gentledot.microservice.microservicechassis.model.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadInputRequestException.class, NumberFormatException.class})
    public ResponseEntity<ResponseError> handleBadInputRequestException(Exception ex) {
        var message = ex.getClass().getSimpleName() + " : " + ex.getMessage();
        ResponseError responseBody = new ResponseError(message, HttpStatus.BAD_REQUEST, "Make sure your input value is correct");
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<ResponseError> handleIoException(Exception ex) {
        var message = "Server error, cause " + ex.getClass().getSimpleName() + " : " + ex.getMessage();
        ResponseError responseBody = new ResponseError(message, HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
