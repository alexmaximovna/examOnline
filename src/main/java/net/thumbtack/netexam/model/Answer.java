package net.thumbtack.netexam.model;

import java.util.Objects;

public class Answer {
    private int answerId;
    private int questionId;
    private String content;
    private int answerNumber;

    public Answer() {
    }

    public Answer(int answerId, String content, int numAnswer) {
        this.answerId = answerId;
        this.content = content;
        this.answerNumber = numAnswer;

    }

    public Answer(int answerId, int questionId, String content, int answerNumber) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.content = content;
        this.answerNumber = answerNumber;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumAnswer() {
        return answerNumber;
    }

    public void setNumAnswer(int numAnswer) {
        this.answerNumber = numAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", content='" + content + '\'' +
                ", numAnswer=" + answerNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getAnswerId() == answer.getAnswerId() &&
                questionId == answer.questionId &&
                answerNumber == answer.answerNumber &&
                Objects.equals(getContent(), answer.getContent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAnswerId(), questionId, getContent(), answerNumber);
    }
}
