package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.AddExamRequest;
import net.thumbtack.netexam.request.AddQuestionRequest;
import net.thumbtack.netexam.request.AddSetQuestionsRequest;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.response.AddSetQuestionsResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGetQuestions extends RestBaseTest {


    @Test
    public void  test (){
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");
        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "a", "b", "c");
        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        AddQuestionRequest q = new AddQuestionRequest("q", 1, answers, 1);
        addQuestionRequests.add(q);
        AddQuestionRequest q1 = new AddQuestionRequest("q1", 2, answers, 1);
        addQuestionRequests.add(q1);
        AddQuestionRequest q2 = new AddQuestionRequest("q2", 3, answers, 1);
        addQuestionRequests.add(q2);

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        HttpEntity<AddSetQuestionsResponse> response2 = addQuestions(addQuestionListRequest,cookieTeacher,examId);
        assertTrue(response2.getBody().toString().contains(q.getQuestion()));
        assertTrue(response2.getBody().toString().contains(q1.getQuestion()));
        assertTrue(response2.getBody().toString().contains(q2.getQuestion()));
        assertTrue(response2.getBody().toString().contains(answers.get(0)));
        assertTrue(response2.getBody().toString().contains(answers.get(1)));
        assertTrue(response2.getBody().toString().contains(answers.get(2)));
    }
}
