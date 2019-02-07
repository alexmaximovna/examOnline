package net.thumbtack.netexam.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Protocol {

    private int protocolId;
    private Student student;
    private int rightAnswerCount;
    private int wrongAnswerCount;
    private int noAnswerCount;
    private Date startTime;
    private Date finishTime;
    private Exam exam;
    private List<StudentQuestions> studentQuestions;


    public Protocol() {
    }


    public Protocol(Exam exam, Student student, List<StudentQuestions> studentQuestionsList, Date time) {
        this.exam = exam;
        this.student = student;
        studentQuestions = studentQuestionsList;
        startTime = time;
    }

    public int getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(int protocolId) {
        this.protocolId = protocolId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getRightAnswerCount() {
        return rightAnswerCount;
    }

    public void setRightAnswerCount(int rightAnswerCount) {
        this.rightAnswerCount = rightAnswerCount;
    }

    public int getWrongAnswerCount() {
        return wrongAnswerCount;
    }

    public void setWrongAnswerCount(int wrongAnswerCount) {
        this.wrongAnswerCount = wrongAnswerCount;
    }

    public int getNoAnswerCount() {
        return noAnswerCount;
    }

    public void setNoAnswerCount(int noAnswerCount) {
        this.noAnswerCount = noAnswerCount;
    }



    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }


    public List<StudentQuestions> getStudentQuestions() {
        return studentQuestions;
    }

    public void setStudentQuestions(List<StudentQuestions> studentQuestions) {
        this.studentQuestions = studentQuestions;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Protocol)) return false;
        Protocol protocol = (Protocol) o;
        return getProtocolId() == protocol.getProtocolId() &&
                getRightAnswerCount() == protocol.getRightAnswerCount() &&
                getWrongAnswerCount() == protocol.getWrongAnswerCount() &&
                getNoAnswerCount() == protocol.getNoAnswerCount() &&
                Objects.equals(getStudent(), protocol.getStudent()) &&
                Objects.equals(getStartTime(), protocol.getStartTime()) &&
                Objects.equals(getFinishTime(), protocol.getFinishTime()) &&
                Objects.equals(getExam(), protocol.getExam()) &&
                Objects.equals(getStudentQuestions(), protocol.getStudentQuestions());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProtocolId(), getStudent(), getRightAnswerCount(), getWrongAnswerCount(), getNoAnswerCount(), getStartTime(), getFinishTime(), getExam(), getStudentQuestions());
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "protocolId=" + protocolId +
                ", student=" + student +
                ", rightAnswerCount=" + rightAnswerCount +
                ", wrongAnswerCount=" + wrongAnswerCount +
                ", noAnswerCount=" + noAnswerCount +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", exam=" + exam +
                ", studentQuestions=" + studentQuestions +
                '}';
    }
}