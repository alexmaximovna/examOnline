package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.SessionDao;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.model.Session;
import net.thumbtack.netexam.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SessionDaoImpl extends BaseDaoImpl implements SessionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDaoImpl.class);

    @Override
    public Session insert(Session session) throws DataBaseException {
        LOGGER.debug("DAO Session  {}", session);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSessionMapper(sqlSession).insert(session.getCookie(),session.getUser().getUserId());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Session  {} {}", session, ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }
            sqlSession.commit();
            LOGGER.debug("DAO Session   {}", session);
        }
        return session;
    }

    @Override
    public void deleteSession(UUID cookie) throws DataBaseException {
        LOGGER.debug("DAO delete Session by id {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSessionMapper(sqlSession).delete(cookie.toString());

            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Session {}", ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }
            sqlSession.commit();
        }

    }

    @Override
    public void deleteSession(String cookie) throws DataBaseException {
        LOGGER.debug("DAO delete Session by cookie {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSessionMapper(sqlSession).delete(cookie);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Session {}", ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }
            sqlSession.commit();
        }
    }

    @Override
    public int getUserIdByCookie(UUID cookies) throws DataBaseException {
            LOGGER.debug("DAO get User by cookies {}", cookies);
            try (SqlSession sqlSession = getSession()) {
                return getSessionMapper(sqlSession).getUserIdByCookies(cookies.toString());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get User{}", ex);
                throw new DataBaseException();
            }

        }

    @Override
    public User getUserByCookie(String cookie) throws DataBaseException {
        LOGGER.debug("DAO get User by cookie {}", cookie);
        User user;
        try (SqlSession sqlSession = getSession()) {
            try {
                user = getSessionMapper(sqlSession).getUserByCookies(cookie);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't get User{}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            LOGGER.debug("DAO Session   {}", user);

        }
        return user;
    }
}
