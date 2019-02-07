package net.thumbtack.netexam.model;

import java.util.Objects;

public class Session {
    private String cookie;
    private User user;

    public Session(){}

    public Session(String cookie, User user) {
        this.cookie = cookie;
        this.user = user;
    }



    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(getCookie(), session.getCookie()) &&
                Objects.equals(getUser(), session.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCookie(), getUser());
    }

    @Override
    public String toString() {
        return "Session{" +
                "cookie='" + cookie + '\'' +
                ", user=" + user +
                '}';
    }
}
