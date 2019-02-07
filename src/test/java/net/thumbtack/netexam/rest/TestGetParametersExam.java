package net.thumbtack.netexam.rest;

import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.*;
import net.thumbtack.netexam.response.StateReadyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGetParametersExam extends RestBaseTest {

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

        HttpEntity<ParametersExamResponse> response2 = getParameters(cookieTeacher,examId);
        assertEquals(r.getQuestionsCountPerExam(),response2.getBody().getQuestionsCountPerExam());
        assertEquals(r.getTimeInMinutes(),response2.getBody().getTimeInMinutes());
        assertEquals(r.getShowDetails(),response2.getBody().getShowDetails());
        assertEquals(examRequest.getSemester(),response2.getBody().getSemester().toString());
        assertEquals(examRequest.getName().toLowerCase(),response2.getBody().getName());
        assertEquals(examId,response2.getBody().getExamId());





    }

    @Test
    public void testAddExam() {
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Алгебра", "5");
        int examId = addExam(examRequest,cookieTeacher);

        HttpEntity<ParametersExamResponse> response2 = getParameters(cookieTeacher,examId);
        assertEquals(0,response2.getBody().getQuestionsCountPerExam());
        assertEquals(0,response2.getBody().getTimeInMinutes());
        assertNull(response2.getBody().getShowDetails());
        assertEquals(examRequest.getSemester(),response2.getBody().getSemester().toString());
        assertEquals(examRequest.getName().toLowerCase(),response2.getBody().getName());
        assertEquals(examId,response2.getBody().getExamId());

    }

}
