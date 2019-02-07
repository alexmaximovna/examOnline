package net.thumbtack.netexam.request;

import net.thumbtack.netexam.validator.LoginConstraint;
import net.thumbtack.netexam.validator.PasswordConstraint;
import net.thumbtack.netexam.validator.PatronymicConstraint;
import net.thumbtack.netexam.validator.PersonalNameConstraint;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RegisterTeacherDtoRequest  {

    @PersonalNameConstraint
    private String firstName;
    @PersonalNameConstraint
    private String lastName;
    @PatronymicConstraint
    private String patronymic;
    @LoginConstraint
    private String login;
    @PasswordConstraint
    private String password;
    @NotBlank
    private String department;
    @NotBlank
    private String position;


    public RegisterTeacherDtoRequest() {
    }

    public RegisterTeacherDtoRequest(@NotBlank String firstName, @NotBlank String lastName, String patronymic,
                                     String login, @NotBlank String password, @NotBlank String department,
                                     @NotBlank String position) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        this.login = login;
        setPassword(password);
        setDepartment(department);
        setPosition(position);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
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
        if (!(o instanceof RegisterTeacherDtoRequest)) return false;
        RegisterTeacherDtoRequest that = (RegisterTeacherDtoRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getDepartment(), that.getDepartment()) &&
                Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getLogin(), getPassword(), getDepartment(), getPosition());
    }

    @Override
    public String toString() {
        return "RegisterTeacherDtoRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
