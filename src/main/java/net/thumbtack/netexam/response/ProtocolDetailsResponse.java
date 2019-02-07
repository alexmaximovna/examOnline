package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class ProtocolDetailsResponse extends ProtocolResponse {
    List<AnswerStatusResponse> answers;

    public ProtocolDetailsResponse() {
    }

    public ProtocolDetailsResponse(int questionsCount, int correct, List<AnswerStatusResponse> answers) {
        super(questionsCount, correct);
        this.answers = answers;
    }

    public ProtocolDetailsResponse(List<AnswerStatusResponse> answers) {
        this.answers = answers;
    }

    public List<AnswerStatusResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerStatusResponse> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProtocolDetailsResponse)) return false;
        if (!super.equals(o)) return false;
        ProtocolDetailsResponse that = (ProtocolDetailsResponse) o;
        return Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getAnswers());
    }

    @Override
    public String toString() {
        return "ProtocolDetailsResponse{" +
                super.toString()+
                "answers=" + answers +
                '}';
    }
}
