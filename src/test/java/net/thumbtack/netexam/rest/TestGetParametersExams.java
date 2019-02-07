package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.SetParametersExamsResponse;
import net.thumbtack.netexam.response.StateReadyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGetParametersExams extends RestBaseTest {
    @Test
    public void testAllFields() throws UnsupportedEncodingException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);

        AddExamRequest examRequest1 = new AddExamRequest("physics", "7");
        int examId1 = addExam(examRequest1,cookieTeacher);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/exams")
                .queryParam("name",URLEncoder.encode("physics","UTF-8"))
                .queryParam("semester","7")
                .queryParam("ready","false");
        HttpEntity<SetParametersExamsResponse> response3 = getWithParameters(cookieTeacher,builder);
        assertEquals(1,response3.getBody().getResponseList().size());



    }

   @Test
    public void testWithoutFields() throws UnsupportedEncodingException {
       RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
               "Иван", "Иванович", "ivanovIv1990", "oldPassword",
               "1", "S");

       String cookieTeacher = registrationTeacherGetCookie(request);

       AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
       int examId = addExam(examRequest,cookieTeacher);


       List<String> answers = new ArrayList<>(3);
       Collections.addAll(answers, "0", "1", "2");

       List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

       AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
       addQuestionRequests.add(q);
       AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
       addQuestionRequests.add(q1);
       AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
       addQuestionRequests.add(q2);

       AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
       addQuestions(addQuestionListRequest,cookieTeacher,examId);

       StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
       HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);

       AddExamRequest examRequest1 = new AddExamRequest("physics", "7");
       int examId1 = addExam(examRequest1,cookieTeacher);

        HttpEntity<SetParametersExamsResponse> response3 = getWithParameters(cookieTeacher,null);
        assertEquals(2,response3.getBody().getResponseList().size());


    }
    @Test
    public void testStatusTrue() throws UnsupportedEncodingException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/exams")
            .queryParam("ready","true");

        HttpEntity<SetParametersExamsResponse> response3 = getWithParameters(cookieTeacher,builder);
        assertEquals(1,response3.getBody().getResponseList().size());



    }
    @Test
    public void testEqualsNameExams() throws UnsupportedEncodingException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("physics", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);

        AddExamRequest examRequest1 = new AddExamRequest("physics", "7");
        int examId1 = addExam(examRequest1,cookieTeacher);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/exams")
                .queryParam("name",URLEncoder.encode("physics","UTF-8"));

        HttpEntity<SetParametersExamsResponse> response3 = getWithParameters(cookieTeacher,builder);
        assertEquals(2,response3.getBody().getResponseList().size());


    }
    @Test
    public void testEqualsSemester() throws UnsupportedEncodingException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("geometry", "7");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        HttpEntity<StateReadyResponse> res = makeStateReady(r,cookieTeacher,examId);

        AddExamRequest examRequest1 = new AddExamRequest("physics", "7");
        int examId1 = addExam(examRequest1,cookieTeacher);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/exams")
                .queryParam("semester","7");

        HttpEntity<SetParametersExamsResponse> response3 = getWithParameters(cookieTeacher,builder);
        assertEquals(2,response3.getBody().getResponseList().size());

    }
}
