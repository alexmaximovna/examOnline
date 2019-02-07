package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.ExamToPassResponse;
import net.thumbtack.netexam.response.ListExamsStudentResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestStudentGetListExams extends RestBaseTest {

    @Test
    public void getAllExams() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest, cookieTeacher);
        AddExamRequest examRequest1 = new AddExamRequest("Geometry", "5");
        int examId1 = addExam(examRequest1, cookieTeacher);
        AddExamRequest examRequest2 = new AddExamRequest("English", "5");
        int examId2 = addExam(examRequest2, cookieTeacher);

        List<String> answers = new ArrayList<>();
        Collections.addAll(answers, "empty", "adc", "dfg","zxc","dfg","zxc");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 2);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 3);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest, cookieTeacher, examId);

        AddSetQuestionsRequest addQuestionListRequest1 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest1, cookieTeacher, examId1);

        AddSetQuestionsRequest addQuestionListRequest2 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest2, cookieTeacher, examId2);

        StateReadyRequest r = new StateReadyRequest(3, 2, ShowDetails.FALSE);
        makeStateReady(r, cookieTeacher, examId);

        StateReadyRequest r1 = new StateReadyRequest(2, 2, ShowDetails.TRUE);
        makeStateReady(r1, cookieTeacher, examId1);

        StateReadyRequest r2 = new StateReadyRequest(3, 2, ShowDetails.TRUE);
        makeStateReady(r2, cookieTeacher, examId2);

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/studentexams")
                .queryParam("filter","ALL");
        ListExamsStudentResponse list = getExamsForPass(cookieStudent,builder);
        assertEquals(3,list.getList().size());

    }

    @Test
    public void getPassedAndRemainingExams() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest, cookieTeacher);
        AddExamRequest examRequest1 = new AddExamRequest("Geometry", "5");
        int examId1 = addExam(examRequest1, cookieTeacher);
        AddExamRequest examRequest2 = new AddExamRequest("English", "5");
        int examId2 = addExam(examRequest2, cookieTeacher);

        List<String> answers = new ArrayList<>();
        Collections.addAll(answers, "empty", "adc", "dfg","zxc","dfg","zxc");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 2);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 3);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest, cookieTeacher, examId);

        AddSetQuestionsRequest addQuestionListRequest1 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest1, cookieTeacher, examId1);

        AddSetQuestionsRequest addQuestionListRequest2 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest2, cookieTeacher, examId2);

        StateReadyRequest r = new StateReadyRequest(3, 2, ShowDetails.FALSE);
        makeStateReady(r, cookieTeacher, examId);

        StateReadyRequest r1 = new StateReadyRequest(2, 2, ShowDetails.TRUE);
        makeStateReady(r1, cookieTeacher, examId1);

        StateReadyRequest r2 = new StateReadyRequest(3, 2, ShowDetails.TRUE);
        makeStateReady(r2, cookieTeacher, examId2);

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);

        HttpEntity<ExamToPassResponse> resp = studentGetExamForPass(cookieStudent, examId);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);

        getResults(request1,cookieStudent,examId);

       UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/studentexams")
                .queryParam("filter","PASSED");
        ListExamsStudentResponse list = getExamsForPass(cookieStudent,builder);
        assertEquals(1,list.getList().size());

        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/studentexams")
                .queryParam("filter","REMAINING");
        ListExamsStudentResponse list1 = getExamsForPass(cookieStudent,builder1);

        assertEquals(2,list1.getList().size());


    }

    @Test
    public void getAtTheMomentExams() throws InterruptedException {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest, cookieTeacher);
        AddExamRequest examRequest1 = new AddExamRequest("Geometry", "5");
        int examId1 = addExam(examRequest1, cookieTeacher);
        AddExamRequest examRequest2 = new AddExamRequest("English", "5");
        int examId2 = addExam(examRequest2, cookieTeacher);

        List<String> answers = new ArrayList<>();
        Collections.addAll(answers, "empty", "adc", "dfg","zxc","dfg","zxc");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();

        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 2);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 3);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest, cookieTeacher, examId);

        AddSetQuestionsRequest addQuestionListRequest1 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest1, cookieTeacher, examId1);

        AddSetQuestionsRequest addQuestionListRequest2 = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest2, cookieTeacher, examId2);

        StateReadyRequest r = new StateReadyRequest(3, 1, ShowDetails.FALSE);
        makeStateReady(r, cookieTeacher, examId);

        StateReadyRequest r1 = new StateReadyRequest(2, 1, ShowDetails.TRUE);
        makeStateReady(r1, cookieTeacher, examId1);

        StateReadyRequest r2 = new StateReadyRequest(3, 2, ShowDetails.TRUE);
        makeStateReady(r2, cookieTeacher, examId2);

        RegisterStudentDtoRequest requestStud = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","iva8526635","jdjvf554",
                "1",5);
        String cookieStudent = registrationStudentGetCookie(requestStud);

        studentGetExamForPass(cookieStudent, examId);
        studentGetExamForPass(cookieStudent, examId1);
        List<Integer> listAnswerStud = new ArrayList<>();
        Collections.addAll(listAnswerStud,1,2,5);
        StudentAnswerRequest request1 = new StudentAnswerRequest(listAnswerStud);
        StudentAnswerRequest request2 = new StudentAnswerRequest(listAnswerStud);
        Thread.sleep(TimeUnit.MINUTES.toMillis(2) + 10);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlPrefix + "/api/studentexams")
                .queryParam("filter","CURRENT");
        ListExamsStudentResponse list = getExamsForPass(cookieStudent,builder);
        getResults(request1,cookieStudent,examId);
        getResults(request2,cookieStudent,examId1);
        assertEquals(2,list.getList().size());


    }

}
