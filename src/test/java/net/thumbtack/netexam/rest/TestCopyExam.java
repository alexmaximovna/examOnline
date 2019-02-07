package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.model.ShowDetails;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.CopyExamResponse;
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
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestCopyExam extends RestBaseTest {

    @Test
    public void testAddExam() {
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

        CopyExamRequest request3 = new CopyExamRequest(examId, "Алгебра", 4);
        HttpEntity<CopyExamResponse> response = copyExam(request3,cookieTeacher);

        assertNotEquals(examRequest.getSemester(),response.getBody().getSemester());
        assertEquals(examRequest.getName().toLowerCase(),response.getBody().getName());
        assertNotEquals(examId,response.getBody().getCopyId());



    }
    @Test
    public void testCopyEmptyQuestions() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван", "Иванович", "ivanovIv1990", "oldPassword",
                "1", "S");

        String cookieTeacher = registrationTeacherGetCookie(request);

        AddExamRequest examRequest = new AddExamRequest("Геометрия", "5");
        int examId = addExam(examRequest,cookieTeacher);

        CopyExamRequest request3 = new CopyExamRequest(examId, "Геометрия", 4);
        HttpEntity<CopyExamResponse> response = copyExam(request3,cookieTeacher);

        assertNotEquals(examRequest.getSemester(),response.getBody().getSemester());
        assertEquals(examRequest.getName().toLowerCase(),response.getBody().getName());
        assertNotEquals(examId,response.getBody().getCopyId());



    }

}
