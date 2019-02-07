package net.thumbtack.netexam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.netexam.endpoint.StudentEndpoint;
import net.thumbtack.netexam.model.UserType;
import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import net.thumbtack.netexam.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;


    @InjectMocks
    private StudentEndpoint studentController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .build();
    }

   /* @TestStudentGetResult
    public void testCreateStudent() throws Exception {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "1",1);
        StudentInfoDtoResponse response = new StudentInfoDtoResponse("Иванов",
                "Иван","Иванович", "1",1,UserType.STUDENT);
        Mockito.when(studentService.register(dto)).thenReturn(response);
        MvcResult result = mockMvc.perform(
                post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(200, result.getResponse().getStatus());
        StudentInfoDtoResponse actualStudent = read(result);
        assertEquals(dto.getFirstName(), actualStudent.getFirstName());



    }*/
    @Test
    public void testCreateFailStudent() throws Exception {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "1",13);
        StudentInfoDtoResponse response = new StudentInfoDtoResponse("Иванов",
                "Иван","Иванович", "1",13,UserType.STUDENT);
        Mockito.when(studentService.register(dto)).thenReturn(response);
        MvcResult result = mockMvc.perform(
                post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        //assertEquals(400, result.getResponse().getStatus());
        //StudentInfoDtoResponse actualStudent = read(result);
        //assertEquals(dto.getFirstName(), actualStudent.getFirstName());
}
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static StudentInfoDtoResponse read(MvcResult result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return  mapper.readValue(result.getResponse().getContentAsString(), StudentInfoDtoResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
