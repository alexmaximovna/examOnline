package net.thumbtack.netexam.response;

import net.thumbtack.netexam.model.UserType;

import java.util.Objects;

public class StudentInfoDtoResponse extends UserInfoResponse {

    private String group;
    private int semester;

    public StudentInfoDtoResponse() {
        super();
    }

    public StudentInfoDtoResponse(String firstName, String lastName, String patronymic, String group, int semester, UserType userType) {
        super(firstName, lastName, patronymic, userType);
        this.group = group;
        this.semester = semester;
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
        if (!(o instanceof StudentInfoDtoResponse)) return false;
        if (!super.equals(o)) return false;
        StudentInfoDtoResponse that = (StudentInfoDtoResponse) o;
        return getSemester() == that.getSemester() &&
                Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getGroup(), getSemester());
    }

    @Override
    public String toString() {
        return "StudentInfoDtoResponse{" + super.toString() +
                "group='" + group + '\'' +
                ", semester=" + semester +
                '}';
    }
}
