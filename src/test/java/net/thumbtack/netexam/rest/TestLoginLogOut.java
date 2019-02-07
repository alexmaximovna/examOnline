package net.thumbtack.netexam.rest;




import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.UserLoginRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class TestLoginLogOut extends RestBaseTest {


    @Test
    public void testRegistLogOutLogIn() {
        //REGISTRATION
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1",1);
        String cookieStudent = registrationStudentGetCookie(request);
        assertNotNull(cookieStudent);

        //LOG OUT
        logOut(cookieStudent);

        //LOG IN
        UserLoginRequest request2 = new UserLoginRequest("ivanovIv1990","jdjvf554");
        String name = login(request2);
        assertEquals(request.getFirstName(),name);

    }
    @Test
    public void LogOutLogIn() {
        //REGISTRATION
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Петров",
                "Петр","Петрович","PETR56892","7649pass",
                "45",3);
        String cookieStudent = registrationStudentGetCookie(request);
        //LOG IN
        UserLoginRequest request2 = new UserLoginRequest("petr56892","7649pass");
        String name = login(request2);
        assertEquals(request.getFirstName(),name);

        //LOG OUT
        logOut(cookieStudent);




    }
    @Test
    public void checkFailLoginOrPassword() {
        //REGISTRATION
        RegisterStudentDtoRequest request = new RegisterStudentDtoRequest("Никитин",
                "Александр", "Петрович", "SASHA5678", "password123",
                "56", 5);
        String studentCookie = registrationStudentGetCookie(request);
        //LOG IN
        UserLoginRequest request2 = new UserLoginRequest("SASHA5678", "7649pass");
        try {
            String name = login(request2);
            } catch (HttpClientErrorException ex) {
            assertEquals(400, ex.getStatusCode().value());
            assertTrue(ex.getResponseBodyAsString().contains("{\"errorCode\":\"AUTHENTICATION_FAILED\",\"field\":\"login or password\",\"message\":\"Authentication is failed,wrong login or password\"}"));

        }


    }




}
