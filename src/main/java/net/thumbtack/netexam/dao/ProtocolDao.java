package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.Protocol;

import java.util.List;

public interface ProtocolDao {
    List<Protocol> getProtocols(int userId) throws DataBaseException;

    void insertProtocol(Protocol protocol) throws DataBaseException;

    Protocol getProtocol(int examId,int studentId) throws DataBaseException;

    Protocol updateProtocol(Protocol protocol);

    List<Protocol> getListProtocol(int examId) throws DataBaseException;

    Protocol getProtocol(Exam exam) throws DataBaseException;

}
