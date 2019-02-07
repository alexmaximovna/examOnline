package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGetSetting extends RestBaseTest {


    @Test
    public void testTeacher(){
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        getSettingTeacher(cookieTeacher);


    }
    @Test
    public void testEmptyCookie(){
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", "S");
        registrationTeacherGetCookie(request);
        getSettingNotLoginOrStudent(null);



    }

    @Test
    public void testStudent() {

        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", 8);
        String studentCookie = registrationStudentGetCookie(request);
        getSettingNotLoginOrStudent(studentCookie);

    }
}
