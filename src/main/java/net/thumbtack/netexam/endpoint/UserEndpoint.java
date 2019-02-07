package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.request.UserLoginRequest;
import net.thumbtack.netexam.response.BaseResponseObject;
import net.thumbtack.netexam.response.UserInfoResponse;
import net.thumbtack.netexam.service.SessionService;
import net.thumbtack.netexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter.HEADER_NAME;

@RestController
public class UserEndpoint extends BaseEndpoint {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private SessionService sessionService ;
    private UserService    userService;

    @Autowired
    public UserEndpoint(UserService userService,SessionService sessionService)
    {
        this.userService = userService;
        this.sessionService = sessionService;
    }


    @PostMapping(value = "/api/session", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) throws DataBaseException, AuthenticationException, ExamException {
        Cookie cookie = new Cookie(COOKIE_NAME ,UUID.randomUUID().toString());
        UserInfoResponse userResponse = userService.login(userLoginRequest,cookie.getValue());
        response.addCookie(cookie);
        return userResponse;

        }

    @DeleteMapping(value = "/api/session",produces =MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseObject logOut(@RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException, ExamException {
        sessionService.deleteSession(getCookie(headers));
        return new BaseResponseObject();
    }

}
