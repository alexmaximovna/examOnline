package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
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
public class TestExamStateReady extends  RestBaseTest {

    @Test
    public void testFailNumberAnswer() {
            RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                    "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                    "1", "S");

            String cookieTeacher = registrationTeacherGetCookie(request);

            AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
            int examId = addExam(examRequest,cookieTeacher);

            List<String> answers = new ArrayList<>(3);
            Collections.addAll(answers, "0", "1", "2");

            List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                AddQuestionRequest q = new AddQuestionRequest(String.valueOf(i), 0, answers, 1);
                addQuestionRequests.add(q);
            }
            AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
            addQuestions(addQuestionListRequest,cookieTeacher,examId);

            StateReadyRequest r = new StateReadyRequest(5,5,ShowDetails.FALSE);

            try {
                makeStateReady(r,cookieTeacher,examId);
            }catch (HttpClientErrorException ex){
                assertEquals(400,ex.getStatusCode().value());
                assertTrue(ex.getResponseBodyAsString().contains("\"NOT_CORRECT_NUMBER_QUESTION\""));
            }
        }
    @Test
    public void testFailQuestionCountPer() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AddQuestionRequest q = new AddQuestionRequest(String.valueOf(i), 0, answers, 1);
            addQuestionRequests.add(q);
        }
        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(0,5,ShowDetails.FALSE);

        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"OVERFLOW_MIN_COUNT_QUESTIONS\""));
        }
    }
    @Test
    public void testFailCountQuestionExam() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        //add 1 question
        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("content", 1, answers, 1);
        addQuestionRequests.add(q);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(0,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"OVERFLOW_MIN_COUNT_QUESTIONS\""));
        }
    }
    @Test
    public void testFailMinTime() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AddQuestionRequest q = new AddQuestionRequest(String.valueOf(i), 0, answers, 1);
            addQuestionRequests.add(q);
        }
        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(5,0,ShowDetails.FALSE);

        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"OVERFLOW_MIN_TIME\""));
        }
    }

    @Test
    public void testFailQuestionEmpty() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "a","b","c");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("content", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest emptyQ = new AddQuestionRequest("", 2, answers, 1);
        addQuestionRequests.add(emptyQ);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);



        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"EMPTY_QUESTION_BODY\""));
        }
    }
    @Test
    public void testFailMInAnswers() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(1);
        Collections.addAll(answers, "a");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("content", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest emptyQ = new AddQuestionRequest("", 2, answers, 1);
        addQuestionRequests.add(emptyQ);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);




        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"OVERFLOW_MIN_COUNT_ANSWERS\""));
        }
    }
    @Test
    public void testFailNotCorrectAnswers() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "a","b","c");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("content", 1, answers, null);
        addQuestionRequests.add(q);
        AddQuestionRequest emptyQ = new AddQuestionRequest("", 2, answers, 1);
        addQuestionRequests.add(emptyQ);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_SPECIFIED_CORRECT_ANSWER\""));
        }
    }
    @Test
    public void testFailNumberAnswer2() {
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
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 1, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);

        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_CORRECT_NUMBER_QUESTION\""));
        }
    }
    @Test
    public void testFailEmptyBodyAnswer() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);


        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "", "2");

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
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"EMPTY_ANSWER\""));
        }
    }
    @Test
    public void testFailNotExistCorrectNum() {
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
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 3);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        addQuestions(addQuestionListRequest,cookieTeacher,examId);



        StateReadyRequest r = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try {
            makeStateReady(r,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_EXITS_CORRECT_NUMBER_ANSWER\""));
        }
    }
    @Test
    public void testAllGood() {
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
        assertEquals(r.getQuestionsCountPerExam(),res.getBody().getQuestionsCountPerExam());

    }

    @Test
    public void testAlreadyReady() {
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
        makeStateReady(r,cookieTeacher,examId);

        StateReadyRequest r1 = new StateReadyRequest(2,5,ShowDetails.FALSE);
        try{
            makeStateReady(r1,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400,ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"EXAM_IS_ALREADY_READY\""));
        }
    }
    }

