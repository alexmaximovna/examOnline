package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestRegistration extends RestBaseTest {
    @Test
    public void registerExistLogin() {
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Никитин",
                "Александр", "Петрович", "SASHA5678", "password123",
                "56", 5);
        RegisterStudentDtoRequest request1 = new RegisterStudentDtoRequest("Васильев",
                "Михаил", "Петрович", "SAsha5678", "86848dfesd",
                "hj", 4);

        HttpEntity<RegisterStudentDtoRequest> httpRequest = new HttpEntity<>(request);
        HttpEntity<RegisterStudentDtoRequest> httpRequest1 = new HttpEntity<>(request1);
        try{
            HttpEntity<StudentInfoDtoResponse> response = template.exchange(urlPrefix + "/api/student", HttpMethod.POST, httpRequest, StudentInfoDtoResponse.class);
            HttpEntity<StudentInfoDtoResponse> response1 = template.exchange(urlPrefix + "/api/student", HttpMethod.POST, httpRequest1, StudentInfoDtoResponse.class);

        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue((ex.getResponseBodyAsString()).contains("\"errorCode\":\"LOGIN_ALREADY_EXIST\",\"field\":\"login\",\"message\":\"Login sasha5678 already exists\""));


        }




    }
    @Test
    public void registerFailSemester() {
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Иванов",
                    "Александр", "Петрович", "SASHA12345", "password123",
                    "56", 20);

       HttpEntity<RegisterStudentDtoRequest> httpRequest = new HttpEntity<>(request);
       try{
       HttpEntity<StudentInfoDtoResponse> response = template.exchange(urlPrefix + "/api/student", HttpMethod.POST, httpRequest, StudentInfoDtoResponse.class);
       }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue((ex.getResponseBodyAsString()).contains("[{\"errorCode\":\"INVALID_SEMESTER_NUMBER\",\"field\":\"semester\",\"message\":\"Value 20 is unacceptable for parameter semester\"}]"));


        }




    }

    @Test
    public void registerFailFirstnameLastNameSemester() {
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("",
                null, "Петрович", "SASHA12345", "password123",
                "56", 20);

        HttpEntity<RegisterStudentDtoRequest> httpRequest = new HttpEntity<>(request);
        try{
            HttpEntity<StudentInfoDtoResponse> response = template.exchange(urlPrefix + "/api/student", HttpMethod.POST, httpRequest, StudentInfoDtoResponse.class);
        }catch (HttpClientErrorException ex){
            assertEquals(400, ex.getStatusCode().value());
            assertTrue((ex.getResponseBodyAsString()).contains("INVALID_LASTNAME"));
            assertTrue((ex.getResponseBodyAsString()).contains("INVALID_FIRSTNAME"));
            assertTrue((ex.getResponseBodyAsString()).contains("INVALID_SEMESTER_NUMBER"));
        }




    }
}
