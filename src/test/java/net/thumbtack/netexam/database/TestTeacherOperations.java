package net.thumbtack.netexam.database;


import net.thumbtack.netexam.database.TestBase;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.UserType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


public class TestTeacherOperations extends TestBase {
    @Before()
    public void clear(){
        clearDatabase();
    }
    @Test
    public void testTeacher() throws DataBaseException, AuthenticationException {
        Teacher teacher = new Teacher("Ivanov","Ivan","Ivanovich","ivanov22",
                    "qwerty123",UserType.TEACHER,"MA","leader");

        teacherDao.insert(teacher);
        assertNotEquals(0, teacher.getTeacherId());
        Teacher teacherFromDB = teacherDao.getById(teacher.getTeacherId());
        assertEquals(teacher, teacherFromDB);
    }
    @Test
    public void testTeacher1() throws DataBaseException, AuthenticationException {
        Teacher teacher = new Teacher("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",UserType.TEACHER,
                "1","S");
        teacherDao.insert(teacher);
        assertNotEquals(0, teacher.getTeacherId());
        Teacher teacherFromDB = teacherDao.getById(teacher.getTeacherId());
        assertEquals(teacher, teacherFromDB);
        teacher.setPassword("ghgjkfk");
        teacherDao.update(teacher);
    }

}


