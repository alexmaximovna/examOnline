package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class AddQuestionsResponse {
    private String question;
    private int id;
    private int number;
    private List<String> answers;
    private int correct;


    public AddQuestionsResponse() {
    }

    public AddQuestionsResponse(String question, int id, int number, List<String> answers, int correct) {
        this.question = question;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
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
        if (!(o instanceof AddQuestionsResponse)) return false;
        AddQuestionsResponse that = (AddQuestionsResponse) o;
        return getId() == that.getId() &&
                getNumber() == that.getNumber() &&
                getCorrect() == that.getCorrect() &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuestion(), getId(), getNumber(), getAnswers(), getCorrect());
    }

    @Override
    public String toString() {
        return "AddQuestionsResponse{" +
                "question='" + question + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", answers=" + answers +
                ", correct=" + correct +
                '}';
    }
}
