package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.UserDao;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.request.UserLoginRequest;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDaoImpl.class);

    public User getUserByLogin(String login) throws DataBaseException {
        LOGGER.debug("DAO get User by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getUserByLogin(login);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User{}", ex);
            throw new DataBaseException();
        }
    }
    public Student getStudentByUserId(int userId) throws DataBaseException {
        LOGGER.debug("DAO get Student by Id {}", userId);
        try (SqlSession sqlSession = getSession()) {
            return getStudentMapper(sqlSession).getByUserId(userId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Student{}", ex);
            throw new DataBaseException();
        }
    }

    public Teacher getTeacherByUserId(int userId) throws DataBaseException {
        LOGGER.debug("DAO get Teacher by Id {}", userId);
        try (SqlSession sqlSession = getSession()) {
            return getTeacherMapper(sqlSession).getByUserId(userId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Teacher{}", ex);
            throw new DataBaseException();
        }
    }
    @Override
    public int getTeacherId(int userId) throws DataBaseException {
        LOGGER.debug("DAO get Teacher Id by userId {}", userId);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getTeacherIdByUserId(userId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User{}", ex);
            throw new DataBaseException();
        }
    }
    public int getUserIdByLogin(String login) throws DataBaseException {
        LOGGER.debug("DAO get UserId by login {}", login);
        try (SqlSession sqlSession = getSession()) {
            return getUserMapper(sqlSession).getUserIdByLogin(login);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get User{}", ex);
            throw new DataBaseException();
        }
    }

    public User getUserByLoginAndPassword(String login,String password) throws  DataBaseException {
        LOGGER.debug("DAO get User by login and password {}" );

        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getUserByLoginAndPassword(login, password);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't get User {}", ex);
                throw new DataBaseException();

            }

        }
    }

    @Override
    public int getTeacherId(String cookie) throws DataBaseException {
        LOGGER.debug("DAO get TeacherId by login and password {}" );
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSessionMapper(sqlSession).getTeacherId(cookie);
            }catch (RuntimeException ex) {
                LOGGER.info("Can't get TeacherId {}", ex);
                throw new DataBaseException(ex);
            }


        }
    }

    @Override
    public void login(String cookie, int userId) throws DataBaseException {
        LOGGER.debug("DAO get User by cookie and id {}" );
        try (SqlSession sqlSession = getSession()) {
            try {
               getSessionMapper(sqlSession).insert(cookie,userId);
            }catch (RuntimeException ex) {
                throw new DataBaseException();
            }
            sqlSession.commit();

        }
    }


}
