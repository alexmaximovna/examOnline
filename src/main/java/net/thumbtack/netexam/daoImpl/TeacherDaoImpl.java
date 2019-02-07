package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.TeacherDao;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.UserType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TeacherDaoImpl extends BaseDaoImpl implements TeacherDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherDaoImpl.class);

    public Teacher insert (Teacher teacher) throws  AuthenticationException {
        LOGGER.debug("DAO Teacher  {}", teacher);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(teacher,UserType.TEACHER);
                getTeacherMapper(sqlSession).insert(teacher);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Teacher  {} {}", teacher, ex);
                sqlSession.rollback();
                throw new AuthenticationException(ErrorCode.LOGIN_ALREADY_EXIST,ex,teacher.getLogin());
            }
            sqlSession.commit();
            LOGGER.debug("DAO Teacher   {}", teacher);
        }
        return teacher;
    }



    @Override
    public Teacher getById(int id) {
        LOGGER.debug("DAO get Teacher by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getTeacherMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Teacher {}", ex);
            throw ex;
        }

    }



    public void deleteAll() {
        LOGGER.debug("DAO delete all TEACHERS {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getTeacherMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all TEACHERS  {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        LOGGER.debug("DAO update Teacher  {} ", teacher);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(teacher,UserType.TEACHER);
                getTeacherMapper(sqlSession).update(teacher);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Teacher {} {} ", teacher, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return teacher;
    }


    @Override
    public List<Teacher> getAllTeachers() throws DataBaseException {
        LOGGER.debug("DAO get all teachers");
        List<Teacher> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list = getTeacherMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get teachers {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return list;
        }
    }

    @Override
    public Teacher getTeacherById(int id) {
        LOGGER.debug("DAO get Teacher by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getTeacherMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Teacher {}", ex);
            throw ex;
        }
    }
}
