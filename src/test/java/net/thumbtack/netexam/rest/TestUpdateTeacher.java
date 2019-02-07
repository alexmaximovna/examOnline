package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoTeacherDtoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUpdateTeacher extends RestBaseTest {

    @Test
    public void testUpdate() {
    //REGISTRATION
    RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
            "Иван","Иванович","ivanovIv1990","jdjvf554",
            "1","S");
    String cookieTeacher = registrationTeacherGetCookie(request);

    UpdateInfoTeacherDtoRequest upDto = new UpdateInfoTeacherDtoRequest("Иванов",
            "Иван","Иванович","jdjvf554","qwerty123",
            "1","S");
    updateTeacher(upDto,cookieTeacher);
    }
    @Test
    public void testFailUpdate() {
        //REGISTRATION
        RegisterTeacherDtoRequest request = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","ivanovIv1990","jdjvf554",
                "1","S");
        String cookieTeacher = registrationTeacherGetCookie(request);

        UpdateInfoTeacherDtoRequest upDto = new UpdateInfoTeacherDtoRequest("Иванов",
                "Иван","Иванович","fhgufhf7","qwerty123",
                "1","S");


       try{
           updateTeacher(upDto,cookieTeacher);
       }catch (HttpClientErrorException ex){
           assertEquals(400, ex.getStatusCode().value());
           assertTrue(ex.getResponseBodyAsString().contains("{\"errorCode\":\"WRONG_OLD_PASSWORD\",\"field\":\"password\",\"message\":\"Passwords do not match\"}"));
       }
    }

}
