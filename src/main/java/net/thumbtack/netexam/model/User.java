package net.thumbtack.netexam.model;

import java.util.Objects;


public class User {


    private int userId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String password;
    private UserType userType;

    public User() {
    }

    public User(int id, String firstName, String lastName, String patronymic, String login, String password, UserType userType) {
        userId = id;
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setPassword(password);
        this.login = login;
        setUserType(userType);
    }


    public User(String firstName, String lastName, String patronymic, String login, String password, UserType userType) {
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setPassword(password);
        this.login = login;
        setUserType(userType);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {

        this.userType = userType;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getPatronymic(), user.getPatronymic()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getUserType() == user.getUserType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getFirstName(), getLastName(), getPatronymic(), getLogin(), getPassword(), getUserType());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
