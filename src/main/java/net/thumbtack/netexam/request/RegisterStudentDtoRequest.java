package net.thumbtack.netexam.request;

import net.thumbtack.netexam.validator.*;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RegisterStudentDtoRequest  {


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
    private String group;
    @SemesterConstraint
    private int semester;


    public RegisterStudentDtoRequest() {
    }
    public RegisterStudentDtoRequest( String firstName,  String lastName, String patronymic,
                                     String login,  String password,  String group, int semester) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        this.login = login;
        setPassword(password);
        setGroup(group);
        setSemester(semester);
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof RegisterStudentDtoRequest)) return false;
        RegisterStudentDtoRequest that = (RegisterStudentDtoRequest) o;
        return getSemester() == that.getSemester() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getLogin(), getPassword(), getGroup(), getSemester());
    }
}
