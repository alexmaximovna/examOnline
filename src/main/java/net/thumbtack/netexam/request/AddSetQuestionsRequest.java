package net.thumbtack.netexam.request;

import java.util.List;
import java.util.Objects;

public class AddSetQuestionsRequest {
    private List<AddQuestionRequest> addQuestionRequestList;


    public AddSetQuestionsRequest() {
    }

    public AddSetQuestionsRequest(List<AddQuestionRequest> addQuestionRequestList) {
        this.addQuestionRequestList = addQuestionRequestList;
    }

    public List<AddQuestionRequest> getAddQuestionRequestList() {
        return addQuestionRequestList;
    }

    public void setAddQuestionRequestList(List<AddQuestionRequest> addQuestionRequestList) {
        this.addQuestionRequestList = addQuestionRequestList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddSetQuestionsRequest)) return false;
        AddSetQuestionsRequest that = (AddSetQuestionsRequest) o;
        return Objects.equals(getAddQuestionRequestList(), that.getAddQuestionRequestList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAddQuestionRequestList());
    }

    @Override
    public String toString() {
        return "AddSetQuestionsRequest{" +
                "addQuestionRequestList=" + addQuestionRequestList +
                '}';
    }
}
