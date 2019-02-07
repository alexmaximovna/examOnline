package net.thumbtack.netexam.response;

import net.thumbtack.netexam.model.ShowDetails;

import java.util.Objects;

public class ParametersExamResponse {
    private String name;
    private int examId;
    private Integer semester;
    private String ready;
    private int questionsCountPerExam;
    private int timeInMinutes;
    private ShowDetails showDetails;

    public ParametersExamResponse() {
    }

    public ParametersExamResponse(String name, int examId, Integer semester, String ready, int questionsCountPerExam, int timeInMinutes, ShowDetails showDetails) {
        this.name = name;
        this.examId = examId;
        this.semester = semester;
        this.ready = ready;
        this.questionsCountPerExam = questionsCountPerExam;
        this.timeInMinutes = timeInMinutes;
        this.showDetails = showDetails;
    }

    public ParametersExamResponse(String name, int examId, Integer semester, String status) {
        this.name = name;
        this.examId = examId;
        this.semester = semester;
        this.ready = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }


    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    public int getQuestionsCountPerExam() {
        return questionsCountPerExam;
    }

    public void setQuestionsCountPerExam(int questionsCountPerExam) {
        this.questionsCountPerExam = questionsCountPerExam;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public ShowDetails getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(ShowDetails showDetails) {
        this.showDetails = showDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParametersExamResponse)) return false;
        ParametersExamResponse that = (ParametersExamResponse) o;
        return getExamId() == that.getExamId() &&
                getQuestionsCountPerExam() == that.getQuestionsCountPerExam() &&
                getTimeInMinutes() == that.getTimeInMinutes() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSemester(), that.getSemester()) &&
                Objects.equals(getReady(), that.getReady()) &&
                getShowDetails() == that.getShowDetails();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getExamId(), getSemester(), getReady(), getQuestionsCountPerExam(), getTimeInMinutes(), getShowDetails());
    }

    @Override
    public String toString() {
        return "ParametersExamResponse{" +
                "name='" + name + '\'' +
                ", examId=" + examId +
                ", semester='" + semester + '\'' +
                ", ready='" + ready + '\'' +
                ", questionsCountPerExam=" + questionsCountPerExam +
                ", timeInMinutes=" + timeInMinutes +
                ", showDetails=" + showDetails +
                '}';
    }
}
