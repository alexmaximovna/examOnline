package net.thumbtack.netexam.model;


import java.util.List;
import java.util.Objects;

public class Teacher extends User {
    private int teacherId;
    private String department;
    private String position;
    private List<Exam> examList;

    public Teacher(){}
    public Teacher(String firstName, String lastName, String patronymic, String login, String password,
                   UserType userType, String department, String position) {
        super(firstName, lastName, patronymic, login, password, userType);
        this.position = position;
        this.department = department;

    }



    public Teacher(int id, String firstName, String lastName, String patronymic, String login, String password, UserType userType, String department, String position) {
        super(id, firstName, lastName, patronymic, login, password, userType);
        this.department = department;
        this.position = position;
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

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return getTeacherId() == teacher.getTeacherId() &&
                Objects.equals(getDepartment(), teacher.getDepartment()) &&
                Objects.equals(getPosition(), teacher.getPosition()) &&
                Objects.equals(getExamList(), teacher.getExamList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getTeacherId(), getDepartment(), getPosition(), getExamList());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", examList=" + examList +
                '}';
    }
}
