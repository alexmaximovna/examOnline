package net.thumbtack.netexam.response;

import java.util.Objects;

public class ProtocolResponse {
    private int questionsCount;
    private int correct;

    public ProtocolResponse() {
    }

    public ProtocolResponse(int questionsCount, int correct) {
        this.questionsCount = questionsCount;
        this.correct = correct;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProtocolResponse)) return false;
        ProtocolResponse that = (ProtocolResponse) o;
        return getQuestionsCount() == that.getQuestionsCount() &&
                getCorrect() == that.getCorrect();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuestionsCount(), getCorrect());
    }

    @Override
    public String toString() {
        return "ProtocolResponse{" +
                "questionsCount=" + questionsCount +
                ", correct=" + correct +
                '}';
    }
}
