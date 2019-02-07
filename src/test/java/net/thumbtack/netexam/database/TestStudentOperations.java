package net.thumbtack.netexam.database;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.model.Session;
import net.thumbtack.netexam.model.Student;

import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.model.UserType;
import net.thumbtack.netexam.request.UserLoginRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestStudentOperations extends TestBase{
    /*@Before()
    public void clear(){
        clearDatabase();
    }
    @TestStudentGetResult
    public void testInsertStudent() throws DataBaseException, AuthenticationException {
        Student student = new Student("Ivanov","Ivan","Ivanovich","ivanov456",
                "fhfj456",UserType.STUDENT,"MPB-605",4);
        studentDao.insert(student);
        assertNotEquals(0, student.getStudentId());
        Student studentFromDB = studentDao.getById(student.getStudentId());
        assertEquals(student, studentFromDB);
        User user = userDao.getUserByLogin(student.getLogin());
        assertNotEquals(0, user.getUserId());
    }
    @TestStudentGetResult
    public void testInsertStudent1() throws DataBaseException, AuthenticationException {
        Student student = new Student("Ivanov","Ivan","Ivanovich","ivanov4",
                "fhfj456",UserType.STUDENT,"MPB-605",4);
        studentDao.insert(student);
        assertNotEquals(0, student.getStudentId());
        Student studentFromDB = studentDao.getById(student.getStudentId());
        assertEquals(student, studentFromDB);
        User user = userDao.getUserByLogin(student.getLogin());
        assertNotEquals(0, user.getUserId());
        Session session =  new Session(user);

    }
    @TestStudentGetResult
    public void testLoginStudent1() throws DataBaseException, AuthenticationException {
        Student student = new Student("Ivanovl","Ivan","Ivanovich","i54nov554",
                "fhfj456",UserType.STUDENT,"MPB-605",4);
        studentDao.insert(student);
        assertNotEquals(0, student.getStudentId());
        Student studentFromDB = studentDao.getById(student.getStudentId());
        assertEquals(student, studentFromDB);
        int userId = userDao.getUserIdByLogin(student.getLogin());
        assertNotEquals(0, userId);

    }
    @TestStudentGetResult
    public void testLoginStudent2() throws DataBaseException, AuthenticationException {
        Student student = new Student("Ivanovl","Ivan","Ivanovich","i54nov554",
                "fhfj456",UserType.STUDENT,"MPB-605",4);
        studentDao.insert(student);
        assertNotEquals(0, student.getStudentId());
        Student studentFromDB = studentDao.getById(student.getStudentId());
        assertEquals(student, studentFromDB);
        String cook = UUID.randomUUID().toString();
        String cook1 = UUID.randomUUID().toString();
        sessionDao.insert(new Session(cook,(User)student));
        System.out.println(cook);
        //User newUser = userDao.getUserByLoginAndPassword("i54nov554","fhfj456",cook1);
        System.out.println(cook1);
    }*/
}
