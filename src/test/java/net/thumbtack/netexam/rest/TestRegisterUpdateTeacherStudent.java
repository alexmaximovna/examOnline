package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoStudentDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoTeacherDtoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestRegisterUpdateTeacherStudent extends RestBaseTest {

    @Test
    public void testUpdate() {
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", 8);
        String cookieStudent = registrationStudentGetCookie(request);
        RegisterTeacherDtoRequest request1 = new RegisterTeacherDtoRequest("Лебедева",
                "Ирина", "Анатольевна", "irina34567", "jdjvf554",
                "1", "S");
        String cookieTeacher = registrationTeacherGetCookie(request1);

        UpdateInfoTeacherDtoRequest upDto1 = new UpdateInfoTeacherDtoRequest("Лебедева",
                "Ирина", "Анатольевна", "jdjvf554", "qwerty123",
                "1", "S");
        updateTeacher(upDto1,cookieTeacher);

        UpdateInfoStudentDtoRequest upDto = new UpdateInfoStudentDtoRequest("Иванов",
                "Иван", "Иванович", "jdjvf554", "qwerty123",
                "1", 8);
        updateStudent(upDto,cookieStudent);

    }
    @Test
    public void testFailUpdate() {

        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", 8);
        String cookieStudent = registrationStudentGetCookie(request);

        RegisterTeacherDtoRequest request1 = new RegisterTeacherDtoRequest("Лебедева",
                "Ирина", "Анатольевна", "irina34567", "jdjvf554",
                "1", "S");
        String cookieTeacher = registrationTeacherGetCookie(request1);


        UpdateInfoTeacherDtoRequest upDto1 = new UpdateInfoTeacherDtoRequest("Лебедева",
                "Ирина", "Анатольевна", "jdjvf554", "qwerty123",
                "1", "S");

       try {
           updateTeacher(upDto1,cookieStudent);
       }catch (HttpClientErrorException ex){
           assertEquals(400, ex.getStatusCode().value());
           assertTrue(ex.getResponseBodyAsString().contains("{\"errorCode\":\"USER_IS_NOT_TEACHER\",\"field\":null,\"message\":\"User isn't teacher\"}"));


       }
    }
}
