package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.ExamToPassResponse;
import net.thumbtack.netexam.response.StateReadyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestStudentGetExamForPass extends RestBaseTest {

    @Test
    public void testExamNotReady() {
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

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",8);
        String cookieStudent = registrationStudentGetCookie(requestStud);

        try {
            HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId1);
        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"EXAM_IS_NOT_READY\""));
        }
    }
    @Test
    public void testExamReady() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "8");
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

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",8);
        String cookieStudent = registrationStudentGetCookie(requestStud);

            HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId);

            assertEquals(r.getQuestionsCountPerExam(),resp.getBody().getQuestions().size());
            assertEquals(examRequest.getName().toLowerCase(),resp.getBody().getName());

    }
}
