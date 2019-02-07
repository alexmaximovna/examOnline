package net.thumbtack.netexam.request;

import net.thumbtack.netexam.validator.PasswordConstraint;
import net.thumbtack.netexam.validator.PatronymicConstraint;
import net.thumbtack.netexam.validator.PersonalNameConstraint;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UpdateInfoTeacherDtoRequest {
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
    private String department;
    @NotBlank
    private String position;

    public UpdateInfoTeacherDtoRequest() {
    }

    public UpdateInfoTeacherDtoRequest(String firstName, String lastName, String patronymic, String oldPassword, String newPassword, @NotBlank String department, @NotBlank String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.department = department;
        this.position = position;
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
        if (!(o instanceof UpdateInfoTeacherDtoRequest)) return false;
        UpdateInfoTeacherDtoRequest that = (UpdateInfoTeacherDtoRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getOldPassword(), that.getOldPassword()) &&
                Objects.equals(getNewPassword(), that.getNewPassword()) &&
                Objects.equals(getDepartment(), that.getDepartment()) &&
                Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getOldPassword(), getNewPassword(), getDepartment(), getPosition());
    }


}
