package net.thumbtack.netexam.exception;

public class ExamException extends Exception {
    private ErrorCode errorCode;

    public ExamException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ExamException() {
        super();
    }

    public ExamException(String message) {
        super(message);
    }

    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamException(Throwable cause) {
        super(cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
