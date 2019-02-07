package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Filter;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.StudentAnswerRequest;
import net.thumbtack.netexam.request.UpdateInfoStudentDtoRequest;
import net.thumbtack.netexam.response.ExamToPassResponse;
import net.thumbtack.netexam.response.ListExamsStudentResponse;
import net.thumbtack.netexam.response.ProtocolResponse;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import net.thumbtack.netexam.service.SessionService;
import net.thumbtack.netexam.service.StudentService;
import net.thumbtack.netexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
public class StudentEndpoint extends BaseEndpoint {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private static final String HEADER_NAME = "COOKIE";

    private final  StudentService studentService;
    private final  UserService userService ;
    private final  SessionService sessionService ;


    @Autowired
    public StudentEndpoint(StudentService studentService, UserService userService, SessionService sessionService)
    {

        this.studentService = studentService;
        this.userService = userService;
        this.sessionService = sessionService;
    }



    @PostMapping(value = "/api/student", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentInfoDtoResponse register(@Valid @RequestBody RegisterStudentDtoRequest studentDtoRequest, HttpServletResponse response) throws DataBaseException, AuthenticationException, ExamException {
        Cookie cookie = new Cookie(COOKIE_NAME,UUID.randomUUID().toString());
        StudentInfoDtoResponse responseStudent = studentService.register(studentDtoRequest);
        sessionService.addSession(userService.getUserByLogin(studentDtoRequest.getLogin()),cookie);
        response.addCookie(cookie);
        return responseStudent;
    }

    @PutMapping(value = "/api/student",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentInfoDtoResponse updateInfo(@Valid @RequestBody UpdateInfoStudentDtoRequest studentDtoRequest, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, AuthenticationException, ExamException {
        User user = sessionService.getUserByCookie(getCookie(headers));
        return studentService.update(studentDtoRequest,user);

    }

    @GetMapping(value = "/api/studentexams",produces = MediaType.APPLICATION_JSON_VALUE)
    public ListExamsStudentResponse getExamsStudent(@RequestHeader(HEADER_NAME) HttpHeaders headers, @RequestParam(required = false,name = "filter")String filter) throws DataBaseException, ExamException {
        return studentService.getExams(Filter.getFilter(filter), sessionService.getUserByCookie(getCookie(headers)));
    }

    @GetMapping(value = "/api/studentexams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamToPassResponse getExamToPass(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return studentService.getExamToPass(sessionService.getUserByCookie(getCookie(headers)), examId);
    }

    @PostMapping(value = "/api/studentexams/{id}/solutions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProtocolResponse getResultsExams(@PathVariable("id") int examId, @RequestHeader(HEADER_NAME) HttpHeaders headers, @RequestBody StudentAnswerRequest request) throws DataBaseException, ExamException {
        return studentService.getResultsExam(sessionService.getUserByCookie(getCookie(headers)), examId, request);
    }

    @GetMapping(value = "/api/studentexams/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProtocolResponse[] getProtocols(@RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        return studentService.getProtocols(sessionService.getUserByCookie(getCookie(headers)));

    }

}
