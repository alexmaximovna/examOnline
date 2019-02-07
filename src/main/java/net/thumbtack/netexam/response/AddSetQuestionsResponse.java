package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class AddSetQuestionsResponse {
    private List<AddQuestionsResponse> addQuestionsResponse;

    public AddSetQuestionsResponse() {
    }

    public AddSetQuestionsResponse(List<AddQuestionsResponse> addQuestionsResponse) {
        this.addQuestionsResponse = addQuestionsResponse;
    }

    public List<AddQuestionsResponse> getAddQuestionsResponse() {
        return addQuestionsResponse;
    }

    public void setAddQuestionsResponse(List<AddQuestionsResponse> addQuestionsResponse) {
        this.addQuestionsResponse = addQuestionsResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddSetQuestionsResponse)) return false;
        AddSetQuestionsResponse that = (AddSetQuestionsResponse) o;
        return Objects.equals(getAddQuestionsResponse(), that.getAddQuestionsResponse());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAddQuestionsResponse());
    }

    @Override
    public String toString() {
        return "AddSetQuestionsResponse{" +
                "addQuestionsResponse=" + addQuestionsResponse +
                '}';
    }
}
