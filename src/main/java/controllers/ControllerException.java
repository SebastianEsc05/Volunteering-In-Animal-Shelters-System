package controllers;

public class ControllerException extends RuntimeException {
    public ControllerException(String message) {
        super(message);
    }
}
