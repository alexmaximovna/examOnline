package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.request.AddExamRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestDeleteExamId extends RestBaseTest {


    @Test
    public void testDeleteExam() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        //1 exam
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);
        //2 exam
        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int examId1 = addExam(examRequest1,cookieTeacher);
        deleteExam( examId,cookieTeacher);


    }
    }
