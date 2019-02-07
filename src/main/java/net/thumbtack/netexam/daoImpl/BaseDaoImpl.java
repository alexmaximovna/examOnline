package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.mappers.*;
import net.thumbtack.netexam.model.Question;
import net.thumbtack.netexam.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class BaseDaoImpl {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected TeacherMapper getTeacherMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(TeacherMapper.class);
    }

    protected StudentMapper getStudentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(StudentMapper.class);
    }
    protected SessionMapper getSessionMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SessionMapper.class);
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected ProtocolMapper getProtocolMapper(SqlSession sqlSession){return  sqlSession.getMapper(ProtocolMapper.class);}

    protected ExamMapper getExamMapper(SqlSession sqlSession){return sqlSession.getMapper(ExamMapper.class);}

    protected QuestionMapper getQuestionMapper(SqlSession sqlSession){return sqlSession.getMapper(QuestionMapper.class);}

    protected AnswerMapper getAnswerMapper(SqlSession sqlSession){return sqlSession.getMapper(AnswerMapper.class);}


}