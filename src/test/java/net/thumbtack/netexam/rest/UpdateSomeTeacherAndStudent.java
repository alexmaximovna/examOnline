package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoStudentDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoTeacherDtoRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UpdateSomeTeacherAndStudent extends RestBaseTest {

    @Test
    public void testUpdate() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","oldPassword",
                "1","S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        RegisterTeacherDtoRequest request1 = new RegisterTeacherDtoRequest("Петров",
                "Петр","Петрович","petrov1234","oldPassword",
                "2","D");
        String cookieTeacher1 = registrationTeacherGetCookie(request1);

       //update password Teacher
        UpdateInfoTeacherDtoRequest upDto = new UpdateInfoTeacherDtoRequest("Иванов",
                "Иван","Иванович","oldPassword","newPassword",
                "1","S");
        updateTeacher(upDto,cookieTeacher);

       UpdateInfoTeacherDtoRequest upDto1 = new UpdateInfoTeacherDtoRequest("Петров",
                "Петр","Петрович","oldPassword","newPassword",
                "2","В");
        updateTeacher(upDto1,cookieTeacher1);

       //Registration StudentsResponse
        RegisterStudentDtoRequest request6 = new  RegisterStudentDtoRequest("Лебедева",
                "Ирина","Ивановна","irina5678","jdjvf554",
                "1",8);
        String cookieStudent = registrationStudentGetCookie(request6);

        RegisterStudentDtoRequest request8 = new  RegisterStudentDtoRequest("Филиппов",
                "Филипп","Андреевич","fil456789","jdjvf554",
                "4",3);
        String cookieStudent1 = registrationStudentGetCookie(request8);

        //updateStudents
        UpdateInfoStudentDtoRequest upDto2 = new UpdateInfoStudentDtoRequest("Лебедева",
                "Ира","Ивановна","jdjvf554","qwerty123",
                "1",8);
        updateStudent(upDto2,cookieStudent);

        UpdateInfoStudentDtoRequest upDto3 = new UpdateInfoStudentDtoRequest("Филиппов",
                "Филипп","Андреевич","jdjvf554","qwerty123",
                "4",3);
        updateStudent(upDto2,cookieStudent1);
        }

}
