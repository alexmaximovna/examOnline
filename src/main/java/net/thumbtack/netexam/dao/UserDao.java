package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.request.UserLoginRequest;

public interface UserDao {
    User getUserByLogin(String login) throws DataBaseException;

    Student getStudentByUserId(int id) throws DataBaseException;

    Teacher getTeacherByUserId(int id) throws DataBaseException;

    int getTeacherId(int userId) throws DataBaseException;

    User getUserByLoginAndPassword(String login,String password) throws DataBaseException, AuthenticationException;

    int getTeacherId(String cookie) throws DataBaseException;


    void login(String cookie, int userId) throws DataBaseException;
}
