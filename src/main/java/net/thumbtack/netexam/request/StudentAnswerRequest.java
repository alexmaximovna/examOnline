package net.thumbtack.netexam.request;

import java.util.List;
import java.util.Objects;

public class StudentAnswerRequest {
    List<Integer> answers;

    public StudentAnswerRequest() { }

    public StudentAnswerRequest(List<Integer> answers) {
        this.answers = answers;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentAnswerRequest)) return false;
        StudentAnswerRequest that = (StudentAnswerRequest) o;
        return Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAnswers());
    }

    @Override
    public String toString() {
        return "StudentAnswerRequest{" +
                "answers=" + answers +
                '}';
    }
}
