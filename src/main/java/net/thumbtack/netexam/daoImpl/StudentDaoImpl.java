package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.StudentDao;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.UserType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

    public Student insert (Student student) throws  AuthenticationException {
        LOGGER.debug("DAO Student {}", student);
        try (SqlSession sqlSession = getSession()) {
            try {

                getUserMapper(sqlSession).insert(student,UserType.STUDENT);
                getStudentMapper(sqlSession).insert(student);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Student  {} {}", student, ex);
                sqlSession.rollback();
                throw new AuthenticationException(ErrorCode.LOGIN_ALREADY_EXIST,ex,student.getLogin());
            }
            sqlSession.commit();
            LOGGER.debug("DAO Student   {}", student);
        }
        return student;
    }

    @Override
    public Student getById(int id) {
        LOGGER.debug("DAO get Student by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getStudentMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Student {}", ex);
            throw ex;
        }

    }


    @Override
    public Student updateAll(Student student) {
        LOGGER.debug("DAO update Student  {} ", student);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(student,UserType.STUDENT);
                getStudentMapper(sqlSession).updateAll(student);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Student {} {} ", student, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return student;

    }

    public int countExam(int userId) throws DataBaseException {
        LOGGER.debug("DAO get Count Exams by userId {}", userId);
        try (SqlSession sqlSession = getSession()) {
            return  getProtocolMapper(sqlSession).getCountExams(userId);

        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Count Exams", ex);
            throw new DataBaseException();
        }


    }


    @Override
    public Student getByUserId(int userId) {
        LOGGER.debug("DAO get Student by UserId {}", userId);
        try (SqlSession sqlSession = getSession()) {
            return getStudentMapper(sqlSession).getByUserId(userId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Student {}", ex);
            throw ex;
        }
    }


    public Student update(Student student) {
        LOGGER.debug("DAO update Student  {} ", student);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(student,UserType.STUDENT);
                getStudentMapper(sqlSession).update(student);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Student {} {} ", student, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return student;

    }
}
