package net.thumbtack.netexam.daoImpl;

import net.thumbtack.netexam.dao.ProtocolDao;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.Protocol;
import net.thumbtack.netexam.utils.FormatUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProtocolDaoImpl extends BaseDaoImpl implements ProtocolDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolDaoImpl.class);
    @Override
    public List<Protocol> getProtocols(int id) throws DataBaseException {
        LOGGER.debug("DAO get all protocols");
        List<Protocol> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list = getProtocolMapper(sqlSession).getProtocolById(id);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get Exam {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return list;
        }
    }
    @Override
    public void insertProtocol(Protocol protocol) throws DataBaseException {
        LOGGER.debug("DAO Protocol {}", protocol);
        try (SqlSession sqlSession = getSession()) {
            try {
                getProtocolMapper(sqlSession).insert(protocol.getExam(),protocol.getStudent(),FormatUtils.getFormat().format(protocol.getStartTime()),protocol);
                getProtocolMapper(sqlSession).insertQuestions(protocol.getStudentQuestions(),protocol.getProtocolId());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Protocol  {} {}", protocol, ex);
                sqlSession.rollback();
                throw new DataBaseException();
            }
            sqlSession.commit();
            LOGGER.debug("DAO Protocol   {}", protocol);
        }

    }

    @Override
    public Protocol getProtocol(int examId, int studentId) throws DataBaseException {
        LOGGER.debug("DAO get Protocol {} {}", examId, studentId);
        try (SqlSession sqlSession = getSession()) {
            return getProtocolMapper(sqlSession).getProtocol(studentId,examId);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Protocol {}", ex);
            throw new DataBaseException();
        }
    }

    @Override
    public Protocol updateProtocol(Protocol protocol) {
        LOGGER.debug("DAO update Protocol  {} ", protocol);
        try (SqlSession sqlSession = getSession()) {
            try {
                getProtocolMapper(sqlSession).update(protocol);
                getProtocolMapper(sqlSession).insertAnswers(protocol.getStudentQuestions(), protocol.getProtocolId());

            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Protocol {} {} ", protocol, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return protocol;
    }


    @Override
    public List<Protocol> getListProtocol(int examId) throws DataBaseException {
        LOGGER.debug("DAO get List protocol by  examId {}", examId);
        List<Protocol> list;
        try (SqlSession sqlSession = getSession()) {
            try{
                list = getProtocolMapper(sqlSession).getProtocolList(examId);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't get Exam {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return list;
        }
    }


    @Override
    public Protocol getProtocol(Exam exam) throws DataBaseException {
        LOGGER.debug("DAO get  protocol by  exam {}", exam);
        Protocol protocol;
        try (SqlSession sqlSession = getSession()) {
            try{
                protocol = getProtocolMapper(sqlSession).getProt(exam);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't get Exam {}", ex);
                throw new DataBaseException();
            }
            sqlSession.commit();
            return protocol;
        }
    }
}
