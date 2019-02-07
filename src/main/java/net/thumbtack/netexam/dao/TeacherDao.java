package net.thumbtack.netexam.dao;


import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher insert(Teacher teacher) throws DataBaseException, AuthenticationException;

    Teacher getById(int id);

    void deleteAll();

    Teacher update(Teacher teacher);

    List<Teacher> getAllTeachers() throws DataBaseException;

    Teacher getTeacherById(int teacherId);

}
