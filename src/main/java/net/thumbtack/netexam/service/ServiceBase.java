package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.*;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.model.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ServiceBase {
    protected ProtocolDaoImpl protocolDao = new ProtocolDaoImpl();
    protected TeacherDaoImpl teacherDao = new TeacherDaoImpl();
    protected StudentDaoImpl studentDao = new StudentDaoImpl();
    protected ExamDaoImpl examDao = new ExamDaoImpl();
    protected UserDaoImpl userDao = new UserDaoImpl();
    protected SettingDaoImpl settingDao = new SettingDaoImpl();
    protected SessionDaoImpl sessionDao = new SessionDaoImpl();
    protected CommonDaoImpl dao = new CommonDaoImpl();


    protected void checkUserIsNotTeacher(User user) throws ExamException {
        if(user.getUserType()!=UserType.TEACHER){
            throw new ExamException(ErrorCode.USER_IS_NOT_TEACHER);
        }
    }

    protected void checkUserIsNotStudent(User user) throws ExamException {
        if(user.getUserType()!=UserType.STUDENT){
            throw new ExamException(ErrorCode.USER_IS_NOT_STUDENT);
        }
    }

    protected void checkUser(User user) throws ExamException {
        if (user == null) {
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
    }

    protected void checkCookie(String cookie) throws ExamException {
        if (cookie == null || StringUtils.isEmpty(cookie)) {
            throw new ExamException(ErrorCode.NULL_COOKIE);
        }
    }
}
