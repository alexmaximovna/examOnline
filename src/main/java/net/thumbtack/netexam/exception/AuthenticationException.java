package net.thumbtack.netexam.exception;

public class AuthenticationException extends Exception {
    private ErrorCode errorCode;
    private String currentValue;

    public AuthenticationException(ErrorCode errorCode, Exception ex) {
        super(ex);
        this.errorCode = errorCode;
    }

    public AuthenticationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public AuthenticationException(ErrorCode errorCode, Exception ex, String currentValue) {
        super(ex);
        this.errorCode = errorCode;
        this.currentValue = currentValue;
    }

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCurrentValue() {
        return currentValue;
    }
}
