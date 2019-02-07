package net.thumbtack.netexam.model;

import java.util.Objects;

public class Student extends User {
    private int studentId;
    private String group;
    private int semester;

    public Student(){}


    public Student(String firstName, String lastName, String patronymic, String login, String password, UserType userType, String group, int semester) {
        super(firstName, lastName, patronymic, login, password, userType);
        this.group = group;
        this.semester = semester;

    }

    public Student(int userId, String firstName, String lastName, String patronymic, String login, String newPassword, UserType userType, int semester, String group) {
        super(userId,firstName,lastName,patronymic,login,newPassword,userType);
        this.semester = semester;
        this.group = group;
    }

    public Student(int userId, String firstName, String lastName, String patronymic, String login, String newPassword, UserType userType) {
        super(userId,firstName,lastName,patronymic,login,newPassword,userType);

    }




    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getStudentId() == student.getStudentId() &&
                getSemester() == student.getSemester() &&
                Objects.equals(getGroup(), student.getGroup());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getStudentId(), getGroup(), getSemester());
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", group='" + group + '\'' +
                ", semester=" + semester +
                '}';
    }
}
