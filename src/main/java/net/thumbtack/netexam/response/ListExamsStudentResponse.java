package net.thumbtack.netexam.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListExamsStudentResponse {
    private List<ExamInfoStudentResponse> list = new ArrayList<>();

    public ListExamsStudentResponse() {
    }

    public ListExamsStudentResponse(List<ExamInfoStudentResponse> list) {
        this.list = list;
    }

    public List<ExamInfoStudentResponse> getList() {
        return list;
    }

    public void setList(List<ExamInfoStudentResponse> list) {
        this.list = list;
    }

    public void add(ExamInfoStudentResponse response) {
        list.add(response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListExamsStudentResponse)) return false;
        ListExamsStudentResponse that = (ListExamsStudentResponse) o;
        return Objects.equals(getList(), that.getList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getList());
    }

    @Override
    public String toString() {
        return "ListExamsStudentResponse{" +
                "list=" + list +
                '}';
    }
}
