package net.thumbtack.netexam.model;


import java.util.Objects;

public class StudentQuestions {
    private int id;
    private Question question;
    private Answer studentAnswer;
    private AnswerStatus status;

    public StudentQuestions() {
    }


    public StudentQuestions(Question q) {
        question = q;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(Answer studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public AnswerStatus getStatus() {
        return status;
    }

    public void setStatus(AnswerStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentQuestions)) return false;
        StudentQuestions that = (StudentQuestions) o;
        return getId() == that.getId() &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getStudentAnswer(), that.getStudentAnswer()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getQuestion(), getStudentAnswer(), getStatus());
    }

    @Override
    public String toString() {
        return "StudentQuestions{" +
                "id=" + id +
                ", question=" + question +
                ", studentAnswer=" + studentAnswer +
                ", status=" + status +
                '}';
    }
}
