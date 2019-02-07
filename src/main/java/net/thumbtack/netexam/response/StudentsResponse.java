package net.thumbtack.netexam.response;

import java.util.Objects;

public class StudentsResponse {
    private String firstName;
    private String lastName;
    private String patronymic;
    private int correct;
    private int wrong;
    private int noAnswer;

    public StudentsResponse() {
    }

    public StudentsResponse(String firstName, String lastName, String patronymic, int correct, int wrong, int noAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.correct = correct;
        this.wrong = wrong;
        this.noAnswer = noAnswer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(int noAnswer) {
        this.noAnswer = noAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentsResponse)) return false;
        StudentsResponse students = (StudentsResponse) o;
        return getCorrect() == students.getCorrect() &&
                getWrong() == students.getWrong() &&
                getNoAnswer() == students.getNoAnswer() &&
                Objects.equals(getFirstName(), students.getFirstName()) &&
                Objects.equals(getLastName(), students.getLastName()) &&
                Objects.equals(getPatronymic(), students.getPatronymic());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getCorrect(), getWrong(), getNoAnswer());
    }

    @Override
    public String toString() {
        return "StudentsResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", correct=" + correct +
                ", wrong=" + wrong +
                ", noAnswer=" + noAnswer +
                '}';
    }
}
