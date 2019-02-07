package net.thumbtack.netexam.request;

import java.util.List;
import java.util.Objects;

public class AddQuestionRequest {
    private String question;
    private Integer number;
    private List<String> answers;
    private Integer correct;

    public AddQuestionRequest() {
    }

    public AddQuestionRequest(String question, Integer number, List<String> answers, Integer correct) {
        this.question = question;
        this.number = number;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddQuestionRequest)) return false;
        AddQuestionRequest that = (AddQuestionRequest) o;
        return Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getAnswers(), that.getAnswers()) &&
                Objects.equals(getCorrect(), that.getCorrect());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuestion(), getNumber(), getAnswers(), getCorrect());
    }

    @Override
    public String toString() {
        return "AddQuestionRequest{" +
                "question='" + question + '\'' +
                ", number=" + number +
                ", answers=" + answers +
                ", correct=" + correct +
                '}';
    }
}



