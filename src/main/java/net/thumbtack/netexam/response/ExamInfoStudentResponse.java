package net.thumbtack.netexam.response;

import java.util.Objects;

public class ExamInfoStudentResponse {
    private int examId;
    private String name;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String department;
    private String position;
    private int questionsCountPerExam;
    private int timeInMinutes;


    public ExamInfoStudentResponse(){}

    public ExamInfoStudentResponse(int examId, String name, String firstName, String lastName, String patronymic, String department, String position, int questionsCountPerExam, int timeInMinutes) {
        this.examId = examId;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.department = department;
        this.position = position;
        this.questionsCountPerExam = questionsCountPerExam;
        this.timeInMinutes = timeInMinutes;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamInfoStudentResponse)) return false;
        ExamInfoStudentResponse that = (ExamInfoStudentResponse) o;
        return getExamId() == that.getExamId() &&
                getQuestionsCountPerExam() == that.getQuestionsCountPerExam() &&
                getTimeInMinutes() == that.getTimeInMinutes() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getDepartment(), that.getDepartment()) &&
                Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getExamId(), getName(), getFirstName(), getLastName(), getPatronymic(), getDepartment(), getPosition(), getQuestionsCountPerExam(), getTimeInMinutes());
    }

    @Override
    public String toString() {
        return "ExamInfoStudentResponse{" +
                "examId=" + examId +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", questionsCountPerExam=" + questionsCountPerExam +
                ", timeInMinutes=" + timeInMinutes +
                '}';
    }
}
