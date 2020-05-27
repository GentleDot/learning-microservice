package net.gentledot.microservice.microservicechassis.model.response;

import org.springframework.http.HttpStatus;

public class ResponseError {
    private String message;
    private int status;
    private String recommendation;

    public ResponseError(String message, int statusCode) {
        this.message = message;
        this.status = statusCode;
    }

    public ResponseError(String message, HttpStatus status) {
        this(message, status, status.getReasonPhrase());
    }

    public ResponseError(String message, HttpStatus status, String recommendation) {
        this.message = message;
        this.status = status.value();
        this.recommendation = recommendation;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(status);
    }

    public String getRecommendation() {
        return recommendation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseError{");
        sb.append("message='").append(message).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
