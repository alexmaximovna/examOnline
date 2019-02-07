package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.Protocol;
import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.Teacher;

import java.util.List;


public interface StudentDao {
    Student insert (Student student) throws DataBaseException,  AuthenticationException;

    Student getById(int id);

    Student update(Student student);

    Student updateAll (Student student);

    int countExam(int userId) throws DataBaseException;

    Student getByUserId(int userId);







}
