package net.thumbtack.netexam.response;

import net.thumbtack.netexam.exception.ErrorCode;

import java.util.Objects;

public class JsonErrorResponse {
    private ErrorCode errorCode;
    private String field;
    private String message;

    public JsonErrorResponse() {
    }

    public JsonErrorResponse(ErrorCode errorCode, String field, String message) {
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonErrorResponse)) return false;
        JsonErrorResponse jsonError = (JsonErrorResponse) o;
        return getErrorCode() == jsonError.getErrorCode() &&
                Objects.equals(getField(), jsonError.getField()) &&
                Objects.equals(getMessage(), jsonError.getMessage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getErrorCode(), getField(), getMessage());
    }

    @Override
    public String toString() {
        return "JsonErrorResponse{" +
                "errorCode=" + errorCode +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
