package net.thumbtack.netexam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private int questionId;
    private String content;
    private int questionNumber;
    private List<Answer> answerList;
    private int rightAnswer;
    private int examId;

    public Question() {
    }

    public Question(String content, int questionNumber, int rightAnswer,int examId) {
        this.content = content;
        this.questionNumber = questionNumber;
        this.rightAnswer = rightAnswer;
        this.examId = examId;
        this.answerList= new ArrayList<>();

    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return getQuestionId() == question.getQuestionId() &&
                getQuestionNumber() == question.getQuestionNumber() &&
                getRightAnswer() == question.getRightAnswer() &&
                getExamId() == question.getExamId() &&
                Objects.equals(getContent(), question.getContent()) &&
                Objects.equals(getAnswerList(), question.getAnswerList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuestionId(), getContent(), getQuestionNumber(), getAnswerList(), getRightAnswer(), getExamId());
    }

    public void addAnswer(Answer answer){
        answerList.add(answer);
    }
}
