package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.ExamDao;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.ExamStatus;
import net.thumbtack.netexam.model.Protocol;
import net.thumbtack.netexam.model.Question;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExamDaoImpl  extends BaseDaoImpl implements ExamDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamDaoImpl.class);

    public Exam insert(Exam exam) throws  ExamException {
        LOGGER.debug("DAO Exam  {}", exam);
        try (SqlSession sqlSession = getSession()) {
            try {
                getExamMapper(sqlSession).insert(exam);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Exam {} {}", exam, ex);
                sqlSession.rollback();
                throw new ExamException(ErrorCode.EXAM_EXIST_IN_SEMESTER);

            }
            sqlSession.commit();
            LOGGER.debug("DAO Exam  {}", exam);
        }
        return exam;

    }

    @Override
    public Exam getExamById(int examId) {
        LOGGER.debug("DAO get Exam by Id {}", examId);
        try (SqlSession sqlSession = getSession()) {
            return getExamMapper(sqlSession).getExamById(examId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Exam {}", ex);
            throw ex;
        }
    }

    @Override
    public Exam update(Exam exam) {
        LOGGER.debug("DAO update Exam  {} ", exam);
        try (SqlSession sqlSession = getSession()) {
            try {
                getExamMapper(sqlSession).update(exam);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Exam {} {} ", exam, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return exam;

    }

    @Override
    public int countExams(int examId) throws DataBaseException {
        LOGGER.debug("DAO count Exam by Id  {} ", examId);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getExamMapper(sqlSession).countExam(examId);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't count Exam {} {} ", examId, ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }



        }
    }

    @Override
    public void deleteExam(int examId) {
        LOGGER.debug("DAO delete Exam {} ");
        try (SqlSession sqlSession = getSession()) {
            try {
                getExamMapper(sqlSession).delete(examId);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Exam {} {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Question> addQuestions(List<Question> questions, int examId) throws DataBaseException {
        LOGGER.debug("DAO  insert questions {}", questions);
        try (SqlSession sqlSession = getSession()) {
            try {
                getQuestionMapper(sqlSession).insert(questions, examId);
                for (Question question: questions) {
                    getAnswerMapper(sqlSession).insert(question.getAnswerList(), question.getQuestionId());

                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't  insert questions {} {} ", questions, ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }
            sqlSession.commit();
        }
        return questions;

    }

    @Override
    public List<Question> getListQuestions(int examId) throws DataBaseException {
        LOGGER.debug("DAO get List questions by  exam Id {}", examId);
        List<Question> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list = getQuestionMapper(sqlSession).getByExamId(examId);

            } catch (RuntimeException ex) {
            LOGGER.info("Can't get Exam {}", ex);
            throw new DataBaseException();
        }
            sqlSession.commit();
        return list;
    }
    }

    @Override
    public Exam insertCopy(Exam exam) throws ExamException {
        LOGGER.debug("DAO Exam  {}", exam);
        try (SqlSession sqlSession = getSession()) {
            try {

                getExamMapper(sqlSession).insert(exam);
                getQuestionMapper(sqlSession).insert(exam.getListQuestion(),exam.getExamId());
                for (Question question: exam.getListQuestion()) {
                    getAnswerMapper(sqlSession).insert(question.getAnswerList(), question.getQuestionId());

                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Exam {} {}", exam, ex);
                sqlSession.rollback();
                throw new ExamException(ErrorCode.EXAM_EXIST_IN_SEMESTER);

            }
            sqlSession.commit();
            LOGGER.debug("DAO Exam  {}", exam);
        }
        return exam;

    }



    @Override
    public List<Exam> getListExam(int teacherId, String name, int semester, ExamStatus status) throws DataBaseException {
        LOGGER.debug("DAO get List exam by  teacherId {}", teacherId);
        List<Exam> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list = getTeacherMapper(sqlSession).getExamList(teacherId,name,semester,status);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't get Exam {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return list;
        }
    }






    @Override
    public List<Exam> getExamsSemester(int semester) throws DataBaseException {
        LOGGER.debug("DAO get all List exam by Semester");
        List<Exam> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list=getExamMapper(sqlSession).getExamBySemester(semester);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get Exam {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return list;
        }
    }
}
