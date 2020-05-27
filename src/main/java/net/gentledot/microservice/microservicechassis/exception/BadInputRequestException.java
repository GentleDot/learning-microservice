package net.gentledot.microservice.microservicechassis.exception;

public class BadInputRequestException extends RuntimeException {
    public BadInputRequestException(String message) {
        super(message);
    }
}
