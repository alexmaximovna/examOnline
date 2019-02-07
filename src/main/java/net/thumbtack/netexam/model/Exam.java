package net.thumbtack.netexam.model;

import java.util.List;
import java.util.Objects;

public class Exam {
    private int examId;
    private String name;
    private int timeExam;
    private int teacherId;
    private Integer semester;
    private ExamStatus status;
    private int questionCount;
    private ShowDetails showDetails;
    private List<Question> listQuestion;

    public Exam(){}


    public Exam(int examId, String name, Integer semester, ExamStatus status) {
        this.examId = examId;
        this.name = name;
        this.semester = semester;
        this.status = status;
    }
    public Exam( String name,int teacherId, int semester, ExamStatus status) {
        this.teacherId = teacherId;
        this.name = name;
        this.semester = semester;
        this.status = status;
    }
    public Exam( String name, int semester, ExamStatus status,int teacherId) {
        this.teacherId = teacherId;
        this.name = name;
        this.semester = semester;
        this.status = status;
    }



    public Exam(int teacherId, String name, Integer semester, ExamStatus notReady, List<Question> listQuestion) {
        this.teacherId = teacherId;
        this.name = name;
        this.semester = semester;
        status = notReady;
        this.listQuestion = listQuestion;
    }

    public Exam(int examId, String name, int timeInMinutes, int teacherId, Integer semester, ExamStatus ready, int questionsCountPerExam, ShowDetails showDetails, List<Question> questionListDB) {
        this.examId = examId;
        this.name = name;
        this.timeExam = timeInMinutes;
        this.teacherId = teacherId;
        this.semester = semester;
        status = ready;
        this.questionCount = questionsCountPerExam;
        this.showDetails = showDetails;
        this.listQuestion = questionListDB;
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

    public int getTimeExam() {
        return timeExam;
    }

    public void setTimeExam(int timeExam) {
        this.timeExam = timeExam;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus ready) {
        this.status = ready;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public ShowDetails getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(ShowDetails showDetails) {
        this.showDetails = showDetails;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", timeExam=" + timeExam +
                ", semester=" + semester +
                ", status='" + status + '\'' +
                ", questionCount=" + questionCount +
                ", showDetails=" + showDetails +
                ", listQuestion=" + listQuestion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exam)) return false;
        Exam exam = (Exam) o;
        return getExamId() == exam.getExamId() &&
                getTimeExam() == exam.getTimeExam() &&
                getTeacherId() == exam.getTeacherId() &&
                getQuestionCount() == exam.getQuestionCount() &&
                Objects.equals(getName(), exam.getName()) &&
                Objects.equals(getSemester(), exam.getSemester()) &&
                getStatus() == exam.getStatus() &&
                getShowDetails() == exam.getShowDetails() &&
                Objects.equals(getListQuestion(), exam.getListQuestion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getExamId(), getName(), getTimeExam(), getTeacherId(), getSemester(), getStatus(), getQuestionCount(), getShowDetails(), getListQuestion());
    }
}
