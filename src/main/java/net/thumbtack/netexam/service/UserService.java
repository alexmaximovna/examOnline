package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.UserDaoImpl;
import net.thumbtack.netexam.endpoint.BaseEndpoint;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.model.UserType;
import net.thumbtack.netexam.request.UserLoginRequest;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import net.thumbtack.netexam.response.TeacherInfoDtoResponse;
import net.thumbtack.netexam.response.UserInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService  extends ServiceBase {


    public UserInfoResponse login(UserLoginRequest userLoginRequest, String cookie) throws DataBaseException, AuthenticationException, ExamException {
        User user = userDao.getUserByLoginAndPassword(userLoginRequest.getLogin().toLowerCase(),userLoginRequest.getPassword());
        if(user == null){
            throw new AuthenticationException(ErrorCode.AUTHENTICATION_FAILED);
        }
        userDao.login(cookie,user.getUserId());
        if (user.getUserType() == UserType.STUDENT) {
            Student student = userDao.getStudentByUserId(user.getUserId());
            checkUser(student);
            return new StudentInfoDtoResponse(student.getFirstName(),student .getLastName(),
                    student.getPatronymic(),student.getGroup(),student.getSemester(),user.getUserType());
        }else {
           Teacher teacher = userDao.getTeacherByUserId(user.getUserId());
           checkUser(teacher);
           return new TeacherInfoDtoResponse(teacher.getFirstName(),teacher.getLastName(),
                   teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),user.getUserType());

        }
    }

    public User getUserByLogin(String login) throws DataBaseException, AuthenticationException {
        User user = userDao.getUserByLogin(login);
        if(user==null){
            throw new AuthenticationException(ErrorCode.INVALID_LOGIN);
        }
        return user;
    }



    public int getTeacherId(String cookie) throws DataBaseException {
        return userDao.getTeacherId(cookie);
    }
}
