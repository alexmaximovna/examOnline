package net.thumbtack.netexam.endpoint;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
public class BaseEndpoint {
    protected String getCookie(HttpHeaders headers) {
        return Arrays.asList(headers.getFirst(HttpHeaders.COOKIE).split("=")).get(1);
    }
}
