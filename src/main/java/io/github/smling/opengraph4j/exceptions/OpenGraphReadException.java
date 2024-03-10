package io.github.smling.opengraph4j.exceptions;

public class OpenGraphReadException extends RuntimeException {
    public OpenGraphReadException(String message) {
        super(message);
    }
    public OpenGraphReadException(String message, Exception exception) {
        super(message, exception);
    }
}
