package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.ExamStatus;
import net.thumbtack.netexam.model.Protocol;
import net.thumbtack.netexam.model.Question;

import java.util.List;

public interface ExamDao {
    Exam insert(Exam exam) throws  ExamException;

    Exam getExamById(int examId);

    Exam update(Exam exam);

    int countExams(int examId) throws DataBaseException;

    void deleteExam(int examId);

    Exam insertCopy(Exam exam) throws ExamException;


    List<Exam> getListExam(int teacherId, String name, int semester, ExamStatus ready) throws DataBaseException;


    List<Exam> getExamsSemester(int semester) throws DataBaseException;


    List<Question> addQuestions( List<Question> questions, int examId) throws DataBaseException;


    List<Question> getListQuestions(int examId) throws DataBaseException;






}
