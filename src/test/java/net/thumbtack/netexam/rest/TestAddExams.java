package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.AddExamRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestAddExams extends RestBaseTest {

    @Test
    public void testAddExam() {

        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        addExam(examRequest,cookieTeacher);

    }

    @Test
    public void testFailAddExams() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра","5");
        addExam(examRequest,cookieTeacher);
        try{
            addExam(examRequest,cookieTeacher);
        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("{\"errorCode\":\"EXAM_EXIST_IN_SEMESTER\",\"field\":null,\"message\":\"Exam exists int this semester\"}"));
        }

    }
    @Test
    public void testAddExams() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);


        AddExamRequest examRequest = new AddExamRequest("Алгебра","5");
        addExam(examRequest,cookieTeacher);
        AddExamRequest examRequest1 = new AddExamRequest("База данных","5");
        addExam(examRequest1,cookieTeacher);


    }
}

