package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.SettingDao;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingDaoImpl extends BaseDaoImpl implements SettingDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDaoImpl.class);

    @Override
    public User getUser(String cookie) throws DataBaseException {
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
