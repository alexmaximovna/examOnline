package net.thumbtack.netexam.rest;


import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.*;
import net.thumbtack.netexam.utils.ConfigUtils;
import net.thumbtack.netexam.utils.MyBatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestBaseTest {

    protected RestTemplate template = new RestTemplate();
    protected static String urlPrefix;
    private static boolean setUpIsDone = false;
    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
            setUpIsDone = true;
        }
    }
    @BeforeClass()
    public static void before() {
        ConfigUtils configUtils = new ConfigUtils();
        urlPrefix = String.format("http://localhost:%d", ConfigUtils.getInt("config.rest_http_port"));

    }
    @Before()
    public void clear(){
        template.exchange(urlPrefix + "/api/debug/clear", HttpMethod.POST,new HttpEntity<>(new BaseResponseObject()),  BaseResponseObject.class);
    }

    protected String registrationStudentGetCookie(RegisterStudentDtoRequest dto){
        HttpEntity<RegisterStudentDtoRequest> request = new HttpEntity<>(dto);
        HttpEntity<StudentInfoDtoResponse> response = template.exchange(urlPrefix + "/api/student", HttpMethod.POST, request, StudentInfoDtoResponse.class);
        assertEquals(request.getBody().getFirstName(),response.getBody().getFirstName());
        assertEquals(request.getBody().getLastName(),response.getBody().getLastName());
        assertEquals(request.getBody().getPatronymic(),response.getBody().getPatronymic());
        assertEquals(request.getBody().getSemester(),response.getBody().getSemester());
        assertEquals(request.getBody().getGroup(),response.getBody().getGroup());
        assertEquals("STUDENT",response.getBody().getUserType().toString());
        HttpHeaders headers = response.getHeaders();
        return Arrays.asList(headers.getFirst(HttpHeaders.SET_COOKIE).split("=")).get(1);

    }
    protected String registrationTeacherGetCookie(RegisterTeacherDtoRequest dto){
        HttpEntity<RegisterTeacherDtoRequest> request = new HttpEntity<>(dto);
        HttpEntity<TeacherInfoDtoResponse> response = template.exchange(urlPrefix + "/api/teacher", HttpMethod.POST, request, TeacherInfoDtoResponse.class);
        assertEquals(request.getBody().getFirstName(),response.getBody().getFirstName());
        assertEquals(request.getBody().getLastName(),response.getBody().getLastName());
        assertEquals(request.getBody().getPatronymic(),response.getBody().getPatronymic());
        assertEquals(request.getBody().getPosition(),response.getBody().getPosition());
        assertEquals(request.getBody().getDepartment(),response.getBody().getDepartment());
        assertEquals("TEACHER",response.getBody().getUserType().toString());
        HttpHeaders headers = response.getHeaders();
        return Arrays.asList(headers.getFirst(HttpHeaders.SET_COOKIE).split("=")).get(1);

    }

    protected void updateStudent(UpdateInfoStudentDtoRequest dto,String cookie){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<UpdateInfoStudentDtoRequest> request = new HttpEntity<>(dto, requestHeaders);
        HttpEntity<StudentInfoDtoResponse> response = template.exchange(urlPrefix + "/api/student", HttpMethod.PUT, request, StudentInfoDtoResponse.class);
        assertEquals(request.getBody().getFirstName(),response.getBody().getFirstName());
        assertEquals(request.getBody().getLastName(),response.getBody().getLastName());
        assertEquals(request.getBody().getPatronymic(),response.getBody().getPatronymic());
        assertEquals(request.getBody().getSemester(),response.getBody().getSemester());
        assertEquals(request.getBody().getGroup(),response.getBody().getGroup());
        assertEquals("STUDENT",response.getBody().getUserType().toString());
    }
    protected void updateTeacher(UpdateInfoTeacherDtoRequest dto,String cookie){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<UpdateInfoTeacherDtoRequest> request = new HttpEntity<>(dto, requestHeaders);
        HttpEntity<TeacherInfoDtoResponse> response = template.exchange(urlPrefix + "/api/teacher", HttpMethod.PUT, request, TeacherInfoDtoResponse.class);
        assertEquals(request.getBody().getFirstName(),response.getBody().getFirstName());
        assertEquals(request.getBody().getLastName(),response.getBody().getLastName());
        assertEquals(request.getBody().getPatronymic(),response.getBody().getPatronymic());
        assertEquals(request.getBody().getPosition(),response.getBody().getPosition());
        assertEquals(request.getBody().getDepartment(),response.getBody().getDepartment());
        assertEquals("TEACHER",response.getBody().getUserType().toString());
    }
    protected void getSettingTeacher(String cookie){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<GetSettingResponse> response2 = template.exchange(urlPrefix + "/api/settings", HttpMethod.GET, newCookie, GetSettingResponse.class);
        assertEquals(ConfigUtils.getInt("config.max_name_length"),response2.getBody().getMaxNameLength());
        assertEquals(ConfigUtils.getInt("config.min_password_length"),response2.getBody().getMinPasswordLength());
        assertEquals(ConfigUtils.getInt("config.min_answers"),response2.getBody().getMinAnswers());
        assertEquals(ConfigUtils.getInt("config.min_questions_count_per_exam"),response2.getBody().getMinQuestionsCountPerExam());
        assertEquals(ConfigUtils.getInt("config.min_time"),response2.getBody().getMinTime());

    }
    protected void getSettingNotLoginOrStudent(String cookie) {

        if (cookie == null || StringUtils.isEmpty(cookie)) {
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders);
            HttpEntity<GetSettingResponse> response1 = template.exchange(urlPrefix + "/api/settings", HttpMethod.GET, newCookie, GetSettingResponse.class);

            assertEquals(ConfigUtils.getInt("config.max_name_length"), response1.getBody().getMaxNameLength());
            assertEquals(ConfigUtils.getInt("config.min_password_length"), response1.getBody().getMinPasswordLength());
            assertEquals(0, response1.getBody().getMinAnswers());
            assertEquals(0, response1.getBody().getMinQuestionsCountPerExam());
            assertEquals(0, response1.getBody().getMinTime());
        } else {
            HttpHeaders requestHeaders2 = new HttpHeaders();
            requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
            HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
            HttpEntity<GetSettingResponse> response2 = template.exchange(urlPrefix + "/api/settings", HttpMethod.GET, newCookie, GetSettingResponse.class);

        assertEquals(ConfigUtils.getInt("config.max_name_length"), response2.getBody().getMaxNameLength());
        assertEquals(ConfigUtils.getInt("config.min_password_length"), response2.getBody().getMinPasswordLength());
        assertEquals(0, response2.getBody().getMinAnswers());
        assertEquals(0, response2.getBody().getMinQuestionsCountPerExam());
        assertEquals(0, response2.getBody().getMinTime());
    }
    }

    protected void logOut(String cookie){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID="+cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<BaseResponseObject> responseLogOut = template.exchange(urlPrefix + "/api/session", HttpMethod.DELETE, newCookie, BaseResponseObject.class);
        assertEquals(responseLogOut.getBody().toString(), "{}");
    }
    protected String login(UserLoginRequest dto){
        HttpEntity<UserLoginRequest> request3 = new HttpEntity<>(dto);
        HttpEntity<UserInfoResponse> responseLogIn = template.exchange(urlPrefix + "/api/session", HttpMethod.POST, request3, UserInfoResponse.class);
        return responseLogIn.getBody().getFirstName();

    }

    protected int addExam(AddExamRequest dto,String cookie){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("COOKIE","JAVASESSIONID="+cookie);
        HttpEntity<AddExamRequest> request2 = new HttpEntity<>(dto,requestHeaders);
        HttpEntity<ExamInfoResponse> response1 = template.exchange(urlPrefix + "/api/exams", HttpMethod.POST, request2, ExamInfoResponse.class);
        assertEquals(dto.getName().toLowerCase(),response1.getBody().getName());
        assertEquals(Integer.parseInt(dto.getSemester()),response1.getBody().getSemester());
        return response1.getBody().getId();

    }
    protected void updateExamSemester(AddExamRequest dto,String cookie,int examId,String semester){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        Map<String, Integer> params = new ConcurrentHashMap<>();
        params.put("id",examId);
        HttpEntity<AddExamRequest> request4 = new HttpEntity<>(dto, requestHeaders2);
        HttpEntity<ExamInfoResponse> response3 = template.exchange(urlPrefix + "/api/exams/{id}", HttpMethod.PUT, request4, ExamInfoResponse.class, params);
        assertEquals(semester, String.valueOf(response3.getBody().getSemester()));
    }
    protected HttpEntity<ExamInfoResponse> updateExamSemesterAndName(AddExamRequest dto, String cookie, int examId, String semester, String name){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE","JAVASESSIONID="+cookie);
        Map<String, Integer> params = new ConcurrentHashMap<>();
        params.put("id",examId);
        HttpEntity<AddExamRequest> request4 = new HttpEntity<>(dto,requestHeaders2);
        HttpEntity<ExamInfoResponse> response3 = template.exchange(urlPrefix + "/api/exams/{id}", HttpMethod.PUT, request4, ExamInfoResponse.class, params);
        return response3;
    }

    protected void deleteExam(int id,String cookieTeacher){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookieTeacher);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        Map<String, Integer> params = new ConcurrentHashMap<>();
        params.put("id",id);
        HttpEntity<BaseResponseObject> responseDeleteExam = template.exchange(urlPrefix + "/api/exams/{id}", HttpMethod.DELETE, newCookie, BaseResponseObject.class,params);
        assertEquals(responseDeleteExam.getBody().toString(), "{}");
    }
    protected HttpEntity<AddSetQuestionsResponse> addQuestions(AddSetQuestionsRequest addQuestionListRequest, String cookie, int examId){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);

        HttpEntity<AddSetQuestionsRequest> addRequest = new HttpEntity<>(addQuestionListRequest, requestHeaders2);
        HttpEntity<AddSetQuestionsResponse> addResponse = template.exchange(urlPrefix + "/api/exams/" + examId + "/questions",
                HttpMethod.POST, addRequest, AddSetQuestionsResponse.class);

        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<AddSetQuestionsResponse> response2 = template.exchange(urlPrefix + "/api/exams/"+examId+"/questions/", HttpMethod.GET, newCookie, AddSetQuestionsResponse.class);
        return response2;


    }
    protected List<AddQuestionRequest> generateQuestions(int countQ,int countAns){
        List<AddQuestionRequest> addQuestionRequests = new ArrayList<>();
        List<String> answers = new ArrayList<>(countAns);
        Collections.addAll(answers, "a", "b", "c");
        for(int i =0;i<countQ;i++){
            AddQuestionRequest q = new AddQuestionRequest("q"+i+1, 1, answers, 1);
            addQuestionRequests.add(q);
        }
     return addQuestionRequests;

    }

    protected HttpEntity<StateReadyResponse> makeStateReady(StateReadyRequest dto, String cookieTeacher, int examId){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookieTeacher);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<StateReadyRequest> stateRequest = new HttpEntity<>(dto,requestHeaders2);
        HttpEntity<StateReadyResponse> res = template.exchange(urlPrefix + "/api/exams/" + examId + "/state",
                HttpMethod.PUT, stateRequest, StateReadyResponse.class);
        return res;
    }

    protected HttpEntity<CopyExamResponse> copyExam(CopyExamRequest dto,String cookie){
        HttpHeaders requestHeaders1 = new HttpHeaders();
        requestHeaders1.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<CopyExamRequest> request4 = new HttpEntity<>(dto, requestHeaders1);
        HttpEntity<CopyExamResponse> response2 = template.exchange(urlPrefix + "/api/exams/", HttpMethod.POST, request4, CopyExamResponse.class);
        return  response2;

    }
    protected HttpEntity<ParametersExamResponse> getParameters(String cookieTeacher,int examId){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookieTeacher);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<ParametersExamResponse> response2 = template.exchange(urlPrefix + "/api/exams/"+examId+"/", HttpMethod.GET, newCookie, ParametersExamResponse.class);
        return response2;
    }

    protected HttpEntity<SetParametersExamsResponse> getWithParameters(String cookieTeacher, UriComponentsBuilder builder){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookieTeacher);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<SetParametersExamsResponse> response3;
        if(builder == null){
            response3 = template.exchange(urlPrefix + "/api/exams", HttpMethod.GET, newCookie, SetParametersExamsResponse.class);
        }else {
            response3 = template.exchange(builder.toUriString(), HttpMethod.GET, newCookie, SetParametersExamsResponse.class);
        }
        return response3;
    }
    protected HttpEntity<ExamToPassResponse> studentGetExamForPass(String cookie, int examId1){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<ExamToPassResponse> response = template.exchange(urlPrefix+"/api/studentexams/"+examId1, HttpMethod.GET, newCookie, ExamToPassResponse.class);
        return response;
    }
    protected ProtocolDetailsResponse getResults(StudentAnswerRequest request1, String cookie, int examId){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(request1,requestHeaders2);
        HttpEntity<ProtocolDetailsResponse> response = template.exchange(urlPrefix+"/api/studentexams/"+examId+"/solutions", HttpMethod.POST, newCookie, ProtocolDetailsResponse.class);
        return response.getBody();

    }
    protected ProtocolDetailsResponse[] getProtocols(String cookie){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<ProtocolDetailsResponse[]> response = template.exchange(urlPrefix+"/api/studentexams/solutions", HttpMethod.GET, newCookie, ProtocolDetailsResponse[].class);
        return response.getBody();
    }

    protected StudentsPassExamResponse reportTeacher(String cookie, int examId){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<StudentsPassExamResponse> response = template.exchange(urlPrefix+"/api/exams/"+examId+"/students", HttpMethod.GET, newCookie, StudentsPassExamResponse.class);
        return response.getBody();
    }

    protected ListExamsStudentResponse getExamsForPass(String cookie, UriComponentsBuilder builder){
        HttpHeaders requestHeaders2 = new HttpHeaders();
        requestHeaders2.add("COOKIE", "JAVASESSIONID=" + cookie);
        HttpEntity<?> newCookie = new HttpEntity<>(requestHeaders2);
        HttpEntity<ListExamsStudentResponse> res = template.exchange(builder.toUriString(), HttpMethod.GET, newCookie, ListExamsStudentResponse.class);
        return res.getBody();
    }
}
