package net.thumbtack.netexam.endpoint;


import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoTeacherDtoRequest;
import net.thumbtack.netexam.response.TeacherInfoDtoResponse;
import net.thumbtack.netexam.service.SessionService;
import net.thumbtack.netexam.service.TeacherService;
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
public class TeacherEndpoint extends BaseEndpoint {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private static final String HEADER_NAME = "COOKIE";

    private SessionService sessionService;
    private TeacherService teacherService;
    private UserService userService;

    @Autowired
    public TeacherEndpoint(TeacherService teacherService, UserService userService, SessionService sessionService) {
        this.teacherService = teacherService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/api/teacher", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherInfoDtoResponse register(@Valid @RequestBody RegisterTeacherDtoRequest teacherDtoRequest, HttpServletResponse response) throws DataBaseException, AuthenticationException, ExamException {
        Cookie cookie = new Cookie(COOKIE_NAME, UUID.randomUUID().toString());
        TeacherInfoDtoResponse responseTeacher = teacherService.register(teacherDtoRequest);
        sessionService.addSession(userService.getUserByLogin(teacherDtoRequest.getLogin()), cookie);
        response.addCookie(cookie);
        return responseTeacher;

    }

    @PutMapping(value = "/api/teacher", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherInfoDtoResponse updateInfo(@Valid @RequestBody UpdateInfoTeacherDtoRequest teacherDtoRequest, @RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, AuthenticationException, ExamException {
        return teacherService.update(teacherDtoRequest, sessionService.getUserByCookie(getCookie(headers)));

    }


}
