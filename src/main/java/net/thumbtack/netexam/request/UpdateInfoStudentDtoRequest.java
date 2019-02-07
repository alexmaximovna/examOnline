package net.thumbtack.netexam.request;

import net.thumbtack.netexam.validator.PasswordConstraint;
import net.thumbtack.netexam.validator.PatronymicConstraint;
import net.thumbtack.netexam.validator.PersonalNameConstraint;
import net.thumbtack.netexam.validator.SemesterConstraint;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UpdateInfoStudentDtoRequest {
    @PersonalNameConstraint
    private String firstName;
    @PersonalNameConstraint
    private String lastName;
    @PatronymicConstraint
    private String patronymic;
    @PasswordConstraint
    private String oldPassword;
    @PasswordConstraint
    private String newPassword;
    @NotBlank
    private String group;
    @SemesterConstraint
    private int semester;

    public UpdateInfoStudentDtoRequest() {
    }

    public UpdateInfoStudentDtoRequest(String firstName, String lastName, String patronymic, String oldPassword, String newPassword, @NotBlank String group, int semester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.group = group;
        this.semester = semester;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
        if (!(o instanceof UpdateInfoStudentDtoRequest)) return false;
        UpdateInfoStudentDtoRequest that = (UpdateInfoStudentDtoRequest) o;
        return getSemester() == that.getSemester() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getOldPassword(), that.getOldPassword()) &&
                Objects.equals(getNewPassword(), that.getNewPassword()) &&
                Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getOldPassword(), getNewPassword(), getGroup(), getSemester());
    }


    @Override
    public String toString() {
        return "UpdateInfoStudentDtoRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", group='" + group + '\'' +
                ", semester=" + semester +
                '}';
    }
}
