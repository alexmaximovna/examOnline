package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoStudentDtoRequest;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUpdateStudent extends RestBaseTest {

    @Test
    public void testUpdate() {
        //REGISTRATION
        RegisterStudentDtoRequest request = new  RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1",8);
        String cookieStudent = registrationStudentGetCookie(request);

        UpdateInfoStudentDtoRequest upDto = new UpdateInfoStudentDtoRequest("Иванов",
                "Иван","Иванович","jdjvf554","qwerty123",
                "1",8);
        updateStudent(upDto,cookieStudent);

    }
    @Test
    public void testUpdate1() {
        //REGISTRATION
        RegisterStudentDtoRequest request = new  RegisterStudentDtoRequest("Лебедева",
                "Ирина","Ивановна","irina5678","password",
                "1",8);
        String cookieStudent = registrationStudentGetCookie(request);

        UpdateInfoStudentDtoRequest upDto = new UpdateInfoStudentDtoRequest("Лебедева",
                "Ира","Ивановна","password","qwerty123",
                "1",8);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("COOKIE","JAVASESSIONID="+cookieStudent);
        HttpEntity<UpdateInfoStudentDtoRequest> request2 = new HttpEntity<>(upDto,requestHeaders);
        HttpEntity<StudentInfoDtoResponse> response1 = template.exchange(urlPrefix + "/api/student", HttpMethod.PUT, request2, StudentInfoDtoResponse.class);
        assertEquals(request.getFirstName(), response1.getBody().getFirstName());
        assertEquals("Ира", response1.getBody().getLastName());
        assertEquals(request.getPatronymic(), response1.getBody().getPatronymic());
        assertEquals(request.getGroup(), response1.getBody().getGroup());
        assertEquals(request.getSemester(), response1.getBody().getSemester());
        assertEquals("STUDENT", response1.getBody().getUserType().toString());
    }


}
