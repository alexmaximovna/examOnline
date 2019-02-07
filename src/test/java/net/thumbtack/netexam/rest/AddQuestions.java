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
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddQuestions extends RestBaseTest {

    @Test
    public void testAddQuestions() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","oldPassword",
                "1","S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");

        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            AddQuestionRequest q = new AddQuestionRequest(String.valueOf(i), 0, answers, 1);
            addQuestionRequests.add(q);
        }

        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        HttpEntity<AddSetQuestionsResponse> response = addQuestions(addQuestionListRequest,cookieTeacher,examId);
        assertEquals(5,response.getBody().getAddQuestionsResponse().size());
    }

    @Test
    public void testFailNegativeNum() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","oldPassword",
                "1","S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");

        int examId = addExam(examRequest,cookieTeacher);

        List<String> answers = new ArrayList<>(3);
        Collections.addAll(answers, "0", "1", "2");

        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            AddQuestionRequest q = new AddQuestionRequest(String.valueOf(i), -1, answers, 1);
            addQuestionRequests.add(q);
        }


        AddSetQuestionsRequest addQuestionListRequest = new AddSetQuestionsRequest(addQuestionRequests);
        try {
            HttpEntity<AddSetQuestionsResponse> response = addQuestions(addQuestionListRequest,cookieTeacher,examId);

        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("\"NOT_CORRECT_NUMBER_QUESTION\""));
        }
    }

}
