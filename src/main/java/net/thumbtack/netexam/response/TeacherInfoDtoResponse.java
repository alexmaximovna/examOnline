package net.thumbtack.netexam.response;

import net.thumbtack.netexam.model.UserType;

import java.util.Objects;

public class TeacherInfoDtoResponse extends UserInfoResponse {

    private String department;
    private String position;


    public TeacherInfoDtoResponse() {
    }

    public TeacherInfoDtoResponse(String firstName, String lastName, String patronymic, String department, String position, UserType userType) {
        super(firstName, lastName, patronymic, userType);
        this.department = department;
        this.position = position;
    }

    public TeacherInfoDtoResponse(String firstName, String lastName, String patronymic, String department, String position) {
        super(firstName, lastName, patronymic);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherInfoDtoResponse)) return false;
        if (!super.equals(o)) return false;
        TeacherInfoDtoResponse that = (TeacherInfoDtoResponse) o;
        return Objects.equals(getDepartment(), that.getDepartment()) &&
                Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getDepartment(), getPosition());
    }
}
