package net.thumbtack.netexam.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetParametersExamsResponse {
    private List<ParametersExamResponse> responseList;


    public SetParametersExamsResponse() {
        this.responseList = new ArrayList<>();
    }

    public SetParametersExamsResponse(List<ParametersExamResponse> responseList) {
        this.responseList = new ArrayList<>();
    }

    public List<ParametersExamResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<ParametersExamResponse> responseList) {
        this.responseList = responseList;
    }

    public void add(ParametersExamResponse response) {

        responseList.add(response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetParametersExamsResponse)) return false;
        SetParametersExamsResponse that = (SetParametersExamsResponse) o;
        return Objects.equals(getResponseList(), that.getResponseList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getResponseList());
    }

    @Override
    public String toString() {
        return "SetParametersExamsResponse{" +
                "responseList=" + responseList +
                '}';
    }
}
