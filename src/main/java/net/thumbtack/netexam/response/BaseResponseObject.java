package net.thumbtack.netexam.response;

public class BaseResponseObject {
    private String response;

    public BaseResponseObject() {
        response = "{}";
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "{}";
    }
}
