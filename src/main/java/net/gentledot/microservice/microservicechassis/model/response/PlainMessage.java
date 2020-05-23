package net.gentledot.microservice.microservicechassis.model.response;

public class PlainMessage {
    private String message;

    protected PlainMessage() {
    }

    public PlainMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
