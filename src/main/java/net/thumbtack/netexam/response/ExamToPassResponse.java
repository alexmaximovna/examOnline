package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class ExamToPassResponse {
    private int examId;
    private String name;
    private TeacherInfoDtoResponse teacher;
    private int timeInMinutes;
    private List<AddQuestionsResponse> questions;

    public ExamToPassResponse() {
    }

    public ExamToPassResponse(int examId, String name, TeacherInfoDtoResponse teacher, int timeInMinutes, List<AddQuestionsResponse> questions) {
        this.examId = examId;
        this.name = name;
        this.teacher = teacher;
        this.timeInMinutes = timeInMinutes;
        this.questions = questions;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherInfoDtoResponse getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherInfoDtoResponse teacher) {
        this.teacher = teacher;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public List<AddQuestionsResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AddQuestionsResponse> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamToPassResponse)) return false;
        ExamToPassResponse that = (ExamToPassResponse) o;
        return getExamId() == that.getExamId() &&
                getTimeInMinutes() == that.getTimeInMinutes() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getTeacher(), that.getTeacher()) &&
                Objects.equals(getQuestions(), that.getQuestions());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getExamId(), getName(), getTeacher(), getTimeInMinutes(), getQuestions());
    }

    @Override
    public String toString() {
        return "ExamToPassResponse{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", timeInMinutes=" + timeInMinutes +
                ", questions=" + questions +
                '}';
    }
}


