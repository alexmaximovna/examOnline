package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.AddExamRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;

import net.thumbtack.netexam.response.ExamInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUpdateExam extends RestBaseTest {
    @Test
    public void testUpdateAllGood() {

       RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
               "Иван", "Иванович", "ivanovIv1990", "jdjvf554",
               "1", "S");
       String cookieTeacher = registrationTeacherGetCookie(request);

       AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
       int examId =addExam(examRequest,cookieTeacher);
       AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
       int examId1 =addExam(examRequest1,cookieTeacher);
       AddExamRequest examRequestUp = new AddExamRequest("Алгебра", "8");
       updateExamSemester(examRequestUp,cookieTeacher,examId,"8");


   }
  @Test
    public void testUpdateSemesterName() {

        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);
        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int examId1 = addExam(examRequest1,cookieTeacher);
        AddExamRequest examRequestUp = new AddExamRequest("mph", "10");
        HttpEntity<ExamInfoResponse> res =updateExamSemesterAndName(examRequestUp,cookieTeacher,examId,"10","mph");
        assertNotEquals(examRequest.getName(),res.getBody().getName());
        assertNotEquals(examRequest.getSemester(),String.valueOf(res.getBody().getSemester()));

    }
    @Test
    public void testUpdateEmptySemester() {

        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int examId1 = addExam(examRequest1,cookieTeacher);

        AddExamRequest examRequestUp = new AddExamRequest("mph", "");
        HttpEntity<ExamInfoResponse> res = updateExamSemesterAndName(examRequestUp,cookieTeacher,examId,String.valueOf(examRequestUp.getSemester()),"mph");
        assertEquals(examRequestUp.getName(),res.getBody().getName());
        assertEquals(examRequest.getSemester(),String.valueOf(res.getBody().getSemester()));

        /*try {

        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_CORRECT_DATA\""));
        }*/
    }
    @Test
    public void testUpdateEmptyName() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        //add Exams
        //1 exam
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId =addExam(examRequest,cookieTeacher);
        //2 exam
        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int id = addExam(examRequest1,cookieTeacher);


        //update 1 exam
        AddExamRequest examRequestUp = new AddExamRequest("", "5");
        HttpEntity<ExamInfoResponse> res = updateExamSemesterAndName(examRequestUp,cookieTeacher,id,examRequestUp.getSemester(),examRequestUp.getName());
        assertEquals(examRequest1.getName().toLowerCase(),res.getBody().getName());
        assertEquals(examRequestUp.getSemester(),String.valueOf(res.getBody().getSemester()));

    }
    @Test
    public void testUpdateEmptyNameSemester() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int id = addExam(examRequest,cookieTeacher);

        //2 exam
        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int id1 = addExam(examRequest1,cookieTeacher);


        //update 1 exam
        AddExamRequest examRequestUp = new AddExamRequest("", "");

        try {
            updateExamSemesterAndName(examRequestUp,cookieTeacher,id,examRequestUp.getSemester(),examRequestUp.getName());
        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_CORRECT_DATA\""));
        }
    }
   @Test
    public void testUpdateFailNoCorrectId() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");

       String cookieTeacher = registrationTeacherGetCookie(request);
       AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
       int id1 = addExam(examRequest,cookieTeacher);

        AddExamRequest examRequest1 = new AddExamRequest("База данных", "6");
        int id = addExam(examRequest1,cookieTeacher);

        AddExamRequest examRequestUp = new AddExamRequest("rt", "3");

        try {
            updateExamSemesterAndName(examRequestUp,cookieTeacher,0,examRequestUp.getSemester(),examRequestUp.getName());
        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("EXAM_NOT_UPDATE"));
        }
    }
}
