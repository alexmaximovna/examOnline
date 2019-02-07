package net.thumbtack.netexam.service;

import net.thumbtack.netexam.dao.SessionDao;
import net.thumbtack.netexam.daoImpl.SessionDaoImpl;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Session;
import net.thumbtack.netexam.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;
@Service
public class SessionService extends ServiceBase {

    public SessionService() {}

    public Session addSession(User user, Cookie cookie) throws DataBaseException, ExamException {
        checkUser(user);
        checkCookie(cookie.getValue());
        Session session = sessionDao.insert(new Session(cookie.getValue(),user));
        if(session == null){
            throw new ExamException(ErrorCode.NULL_SESSION);
        }
        return session;


    }

    public void deleteSession(String cookie) throws DataBaseException, ExamException {
        checkCookie(cookie);
        sessionDao.deleteSession(cookie);
    }



    public User getUserByCookie(String cookie) throws DataBaseException, ExamException {
        checkCookie(cookie);
        User user = sessionDao.getUserByCookie(cookie);
        checkUser(user);
        return  user;
    }
}
