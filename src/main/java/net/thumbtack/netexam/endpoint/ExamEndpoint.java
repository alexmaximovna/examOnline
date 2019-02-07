package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.request.AddExamRequest;
import net.thumbtack.netexam.request.AddSetQuestionsRequest;
import net.thumbtack.netexam.request.CopyExamRequest;
import net.thumbtack.netexam.request.StateReadyRequest;
import net.thumbtack.netexam.response.*;
import net.thumbtack.netexam.service.ExamService;
import net.thumbtack.netexam.service.SessionService;
import net.thumbtack.netexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExamEndpoint extends BaseEndpoint {

    private static final String HEADER_NAME = "COOKIE";
    private SessionService sessionService;
    private UserService userService;
    private ExamService examService;

    @Autowired
    public ExamEndpoint(UserService userService, SessionService sessionService, ExamService examService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.examService = examService;
    }

    @PostMapping(value = "/api/exams", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExamInfoResponse addExam(@Valid @RequestBody AddExamRequest request, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.addExam(request, userService.getTeacherId(getCookie(headers)));

    }

    @PutMapping(value = "/api/exams/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExamInfoResponse updateExam(@PathVariable("id") int examId, @Valid @RequestBody AddExamRequest request, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.updateExam(request, sessionService.getUserByCookie(getCookie(headers)), examId);

    }

    @DeleteMapping(value = "/api/exams/{id}")
    public BaseResponseObject deleteExam(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.deleteExam(sessionService.getUserByCookie(getCookie(headers)), examId);
    }

    @PostMapping(value = "/api/exams/{id}/questions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddSetQuestionsResponse setQuestionsAnswers(@PathVariable("id") int examId, @Valid @RequestBody AddSetQuestionsRequest request, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {

        return examService.addQuestions(examId, sessionService.getUserByCookie(getCookie(headers)), request);
    }

    @PutMapping(value = "/api/exams/{id}/state", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StateReadyResponse changeExamStatus(@PathVariable("id") int examId, @Valid @RequestBody StateReadyRequest request, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.changeStatus(examId, sessionService.getUserByCookie(getCookie(headers)), request);
    }

    @PostMapping(value = "/api/exams/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CopyExamResponse copyExams(@Valid @RequestBody CopyExamRequest request, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.copyExam(sessionService.getUserByCookie(getCookie(headers)), request);
    }

    @GetMapping(value = "/api/exams/{id}/questions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddSetQuestionsResponse getQuestionsExam(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.getQuestions(sessionService.getUserByCookie(getCookie(headers)), examId);
    }

    @GetMapping(value = "/api/exams/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ParametersExamResponse getParamExam(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.getParamExam(sessionService.getUserByCookie(getCookie(headers)), examId);
    }

    @GetMapping(value = "/api/exams", produces = MediaType.APPLICATION_JSON_VALUE)
    public SetParametersExamsResponse getParamAllExam(@RequestHeader(HEADER_NAME) HttpHeaders headers, @RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "semester") Integer semester, @RequestParam(required = false, name = "ready") Boolean ready) throws DataBaseException, ExamException {
        return examService.getParamExams(sessionService.getUserByCookie(getCookie(headers)), name, semester, ready);
    }

    @GetMapping(value = "/api/exams/{id}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentsPassExamResponse listStudentPassExam(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return examService.getListStudentPassExam(sessionService.getUserByCookie(getCookie(headers)), examId);
    }

}
