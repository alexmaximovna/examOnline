package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.AnswerStatusResponse;
import net.thumbtack.netexam.response.ExamToPassResponse;
import net.thumbtack.netexam.response.ProtocolDetailsResponse;
import net.thumbtack.netexam.response.ProtocolResponse;
import net.thumbtack.netexam.response.StateReadyResponse;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestStudentGetResult extends RestBaseTest {
    @org.junit.Test
    public void testExamWithoutDetails()  {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Петров",
                "Петр", "Петрович", "petrovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        //1 exam ready
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);
        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "m","abc", "deg", "zxc");
        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 2);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        Collections.addAll( addQuestionRequests, q,q1,q2);
        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);
        StateReadyRequest r = new StateReadyRequest(3,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);


        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);
        //get exam
        HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);

        ProtocolResponse result = getResults(request1,cookieStudent,examId);
        assertEquals(3,result.getQuestionsCount());

    }
    @org.junit.Test
    public void testExamWithDetails()  {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Петров",
                "Петр", "Петрович", "petrovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        //2 exam ready
        AddExamRequest examRequest1 = new AddExamRequest("Geometry", "5");
        int examId1 = addExam(examRequest1,cookieTeacher);
        List<String> answers1 = new ArrayList<>(3);
        Collections.addAll(answers1, "m","abc", "deg", "zxc");
        List<AddQuestionRequest> addQuestionRequests1 = new ArrayList<>();
        AddQuestionRequest q3 = new AddQuestionRequest("q4", 1, answers1, 1);
        AddQuestionRequest q4 = new AddQuestionRequest("q5", 2, answers1, 3);
        AddQuestionRequest q5 = new AddQuestionRequest("q6", 3, answers1, 2);
        Collections.addAll( addQuestionRequests1, q3,q4,q5);
        AddSetQuestionsRequest addQuestionListRequest1 = new AddSetQuestionsRequest(addQuestionRequests1);
        addQuestions(addQuestionListRequest1,cookieTeacher,examId1);
        StateReadyRequest r1 = new StateReadyRequest(2,5,ShowDetails.TRUE);
        HttpEntity<StateReadyResponse> res1 = makeStateReady(r1,cookieTeacher,examId1);

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);
        //get exam
        HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId1);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);
        //Thread.sleep(TimeUnit.MINUTES.toMillis(1) + 10);
        ProtocolDetailsResponse result = getResults(request1,cookieStudent,examId1);
        assertEquals(2,result.getQuestionsCount());

    }

    @org.junit.Test
    public void testTimeOut() throws InterruptedException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Петров",
                "Петр", "Петрович", "petrovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        //2 exam ready
        AddExamRequest examRequest1 = new AddExamRequest("Geometry", "5");
        int examId1 = addExam(examRequest1,cookieTeacher);
        List<String> answers1 = new ArrayList<>(3);
        Collections.addAll(answers1, "m","abc", "deg", "zxc");
        List<AddQuestionRequest> addQuestionRequests1 = new ArrayList<>();
        AddQuestionRequest q3 = new AddQuestionRequest("q4", 1, answers1, 1);
        AddQuestionRequest q4 = new AddQuestionRequest("q5", 2, answers1, 3);
        AddQuestionRequest q5 = new AddQuestionRequest("q6", 3, answers1, 2);
        Collections.addAll( addQuestionRequests1, q3,q4,q5);
        AddSetQuestionsRequest addQuestionListRequest1 = new AddSetQuestionsRequest(addQuestionRequests1);
        addQuestions(addQuestionListRequest1,cookieTeacher,examId1);
        StateReadyRequest r1 = new StateReadyRequest(2,1,ShowDetails.TRUE);
        HttpEntity<StateReadyResponse> res1 = makeStateReady(r1,cookieTeacher,examId1);

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);
        //get exam
        HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId1);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);
        Thread.sleep(TimeUnit.MINUTES.toMillis(2) + 10);
        ProtocolDetailsResponse result = getResults(request1,cookieStudent,examId1);
        assertEquals(2,result.getQuestionsCount());
        assertEquals(result.getAnswers().get(0),AnswerStatusResponse.NO_ANSWER);

    }
    @org.junit.Test
    public void testRepeatPass()  {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Петров",
                "Петр", "Петрович", "petrovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);
        //1 exam ready
        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);
        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "m","abc", "deg", "zxc");
        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 2);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        Collections.addAll( addQuestionRequests, q,q1,q2);
        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);
        StateReadyRequest r = new StateReadyRequest(3,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);


        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);
        //get exam
        HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);
        ProtocolResponse result = getResults(request1,cookieStudent,examId);
        assertEquals(3,result.getQuestionsCount());
        try{
            HttpEntity<ExamToPassResponse> resp1 = studentGetExamForPass(cookieStudent, examId);
        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"EXAM_IS_PASSED\""));
        }
    }
}
