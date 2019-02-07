package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.StudentDaoImpl;
import net.thumbtack.netexam.exception.*;
import net.thumbtack.netexam.model.*;
import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
import net.thumbtack.netexam.request.StudentAnswerRequest;
import net.thumbtack.netexam.request.UpdateInfoStudentDtoRequest;
import net.thumbtack.netexam.response.*;
import net.thumbtack.netexam.response.AddQuestionsResponse;
import net.thumbtack.netexam.response.StudentInfoDtoResponse;
import net.thumbtack.netexam.response.TeacherInfoDtoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StudentService extends ServiceBase {


    public StudentInfoDtoResponse register(RegisterStudentDtoRequest dto) throws AuthenticationException, ExamException {

        Student student = studentDao.insert(new Student(dto.getFirstName(),dto.getLastName(),dto.getPatronymic(),
                dto.getLogin().toLowerCase() ,dto.getPassword(),UserType.STUDENT,dto.getGroup(),dto.getSemester()));
        checkUser(student);
        return  new StudentInfoDtoResponse(student.getFirstName(),student.getLastName(),
                student.getPatronymic(),student.getGroup(),student.getSemester(),student.getUserType());
    }

    public StudentInfoDtoResponse update(UpdateInfoStudentDtoRequest dto, User user) throws  DataBaseException, AuthenticationException, ExamException {
        checkUserIsNotStudent(user);
        if (!user.getPassword().equals(dto.getOldPassword())) {
            throw new AuthenticationException(ErrorCode.WRONG_OLD_PASSWORD);

        }

        if(studentDao.countExam(user.getUserId())!=0){
            Student student1 = studentDao.update(new Student(user.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getPatronymic(),
                    user.getLogin(), dto.getNewPassword(), UserType.STUDENT));
            checkUser(student1);
            return new StudentInfoDtoResponse(student1.getFirstName(), student1.getLastName(),
                    student1.getPatronymic(), student1.getGroup(), student1.getSemester(), student1.getUserType());
        }else {
            Student student = studentDao.updateAll(new Student(user.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getPatronymic(),
                    user.getLogin(), dto.getNewPassword(), UserType.STUDENT, dto.getSemester(), dto.getGroup()));
            checkUser(student);
            return new StudentInfoDtoResponse(student.getFirstName(), student.getLastName(),
                    student.getPatronymic(), student.getGroup(), student.getSemester(), student.getUserType());
        }

    }




    public ListExamsStudentResponse getExams(Filter f, User user) throws ExamException, DataBaseException {
        checkUserIsNotStudent(user);
        Student student = studentDao.getByUserId(user.getUserId());
        int semester = student.getSemester();
        Filter filter = (f == null ? Filter.ALL : f);
        List<Teacher> teacherList = teacherDao.getAllTeachers();
        List<Exam> listExam = examDao.getExamsSemester(student.getSemester());

        if(teacherList == null){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        if (filter == Filter.ALL) {
            return getAllExams(teacherList, listExam);
        }

        List<Protocol> protocolList = protocolDao.getProtocols(student.getStudentId());

        if(filter == Filter.PASSED){
            return getPassedExams(teacherList,protocolList,semester);
        }

        if(filter == Filter.REMAINING){
            return getRemainingExams(teacherList,protocolList,listExam);
        }
        return getCurrentExams(teacherList,protocolList,semester);
    }


    public ExamToPassResponse getExamToPass(User user, int examId) throws ExamException, DataBaseException {
        checkUserIsNotStudent(user);
        Exam exam = checkReadyState(examId);
        Student student = studentDao.getByUserId(user.getUserId());
        checkUser(student);
        Teacher teacher =teacherDao.getTeacherById(exam.getTeacherId());
        checkUser(teacher);
        if(exam.getSemester() != student.getSemester()){
            throw new ExamException(ErrorCode.EXAM_NOT_STUDENT_SEMESTER);
        }

        List<Question> questionList = generateRandomQuestions(exam);


        protocolDao.insertProtocol(new Protocol(exam,student,studentQuestionsList(questionList),new Date()));

        return new ExamToPassResponse(examId, exam.getName(),
                new TeacherInfoDtoResponse(teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),teacher.getUserType()),
                exam.getTimeExam(),getList(questionList));


    }

    public ProtocolResponse getResultsExam(User user, int examId, StudentAnswerRequest request) throws ExamException, DataBaseException {
        checkUserIsNotStudent(user);
        Exam exam = checkReadyState(examId);
        Student student = studentDao.getByUserId(user.getUserId());
        if(student==null){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        Protocol protocol = protocolDao.getProtocol(exam.getExamId(),student.getStudentId());
        if (protocol.getFinishTime() != null) {
            throw new ExamException(ErrorCode.EXAM_IS_PASSED);
        }

        protocol.setFinishTime(new Date());

        long examTime = TimeUnit.MILLISECONDS.toMinutes(protocol.getFinishTime().getTime() - protocol.getStartTime().getTime());
        if (examTime > exam.getTimeExam() || request.getAnswers() == null) {
            protocol.setStudentQuestions(nullAnswers(protocol.getStudentQuestions()));
        } else {
            checkStudentAnswers(protocol, request.getAnswers());
        }
        protocol = protocolDao.updateProtocol(protocol);

        return exam.getShowDetails() == ShowDetails.FALSE ? getProtocol(protocol)  :
                getProtocolDetails(protocol)    ;

    }



    public ProtocolResponse[] getProtocols(User user) throws ExamException, DataBaseException {
        checkUserIsNotStudent(user);
        Student student = studentDao.getByUserId(user.getUserId());
        List<Protocol> protocols = protocolDao.getProtocols(student.getStudentId());
        ProtocolResponse[] response = new ProtocolResponse[protocols.size()];
        for (int i = 0; i < protocols.size(); i++) {

            Protocol p = protocols.get(i);
            response[i] = (p.getExam().getShowDetails() == ShowDetails.TRUE ? getProtocolDetails(p) :
                    getProtocol(p));
        }
        return response;

    }





    private ProtocolDetailsResponse getProtocolDetails(Protocol protocol) {
        List<AnswerStatusResponse> answerStatus = new ArrayList<>();
        for (StudentQuestions sq: protocol.getStudentQuestions()) {
            answerStatus.add((sq.getStatus() == AnswerStatus.YES ? AnswerStatusResponse.YES :
                    (sq.getStatus() == AnswerStatus.NO ? AnswerStatusResponse.NO :
                            AnswerStatusResponse.NO_ANSWER)));
        }
        return new ProtocolDetailsResponse(protocol.getStudentQuestions().size(),
                protocol.getRightAnswerCount(), answerStatus);
    }



    private ProtocolResponse getProtocol(Protocol protocol) {
        return new ProtocolResponse(protocol.getStudentQuestions().size(), protocol.getRightAnswerCount());
    }


    private List<StudentQuestions> nullAnswers(List<StudentQuestions> studentQuestions) {
        List<StudentQuestions>  list = new ArrayList<>(studentQuestions);
        for(StudentQuestions sq : list){
            sq.setStudentAnswer(null);
            sq.setStatus(AnswerStatus.NO_ANSWER);
        }
      return list;
    }


    private ListExamsStudentResponse getCurrentExams(List<Teacher> teacherList, List<Protocol> protocolList,int semester) throws ExamException {
        ListExamsStudentResponse responseList = new ListExamsStudentResponse();
        if(protocolList == null){
            return  responseList;
        }
        List<Protocol> currentList = protocolList.stream().filter((p)->TimeUnit.MILLISECONDS.toMinutes(p.getExam().getTimeExam())+p.getStartTime().getTime()<new Date().getTime()&&p.getStudent().getSemester()==semester).collect(Collectors.toList());;
        if(currentList == null || currentList.size()==0){
            return  responseList;
        }
        for(Protocol protocol : currentList){
            Exam exam = protocol.getExam();
            Teacher teacher = teacherList.stream().filter((p)->(p.getExamList().contains(exam))).findFirst().get();
            ExamInfoStudentResponse examInfo = new ExamInfoStudentResponse(exam.getExamId(),exam.getName(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),exam.getQuestionCount(),exam.getTimeExam());
            responseList.add(examInfo);
        }
        return responseList;

    }

    private ListExamsStudentResponse getRemainingExams(List<Teacher> teacherList, List<Protocol> protocolList,List<Exam> examList) throws ExamException {
        ListExamsStudentResponse responseList = new ListExamsStudentResponse();

        if(protocolList == null || protocolList.size()==0){
            for(Exam exam : examList){
                Teacher teacher = teacherList.stream().filter((p)->(p.getTeacherId()==exam.getTeacherId())).findFirst().get();
                ExamInfoStudentResponse examInfo = new ExamInfoStudentResponse(exam.getExamId(),exam.getName(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),exam.getQuestionCount(),exam.getTimeExam());
                responseList.add(examInfo);
            }
            return responseList;
        }
        List<Exam> listExamFromProtocol = protocolList.stream().map(Protocol::getExam).collect(Collectors.toList());
        examList.removeAll(listExamFromProtocol);

        if(examList.size() == 0){
            return responseList;
        }else{
        for(Exam exam : examList){
            Teacher teacher = teacherList.stream().filter((p)->(p.getTeacherId()==exam.getTeacherId())).findFirst().get();
            ExamInfoStudentResponse examInfo = new ExamInfoStudentResponse(exam.getExamId(),exam.getName(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),exam.getQuestionCount(),exam.getTimeExam());
            responseList.add(examInfo);
        }
        return responseList;
        }
    }

    private ListExamsStudentResponse getPassedExams(List<Teacher> teacherList, List<Protocol> protocolList,int semester) throws ExamException {
        ListExamsStudentResponse responseList = new ListExamsStudentResponse();

        if(protocolList == null){
            return  responseList;
        }

        List<Protocol> passList = protocolList.stream().filter((p)->p.getStudent().getSemester()==semester&&p.getFinishTime()!=null).collect(Collectors.toList());;

        if(passList == null || passList.size()==0){
            return  responseList;
        }
        for(Protocol protocol : passList){
            Exam exam = protocol.getExam();

            Teacher teacher = teacherList.stream().filter((p)->(p.getExamList().contains(exam))).findFirst().get();

            ExamInfoStudentResponse examInfo = new ExamInfoStudentResponse(exam.getExamId(),exam.getName(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),exam.getQuestionCount(),exam.getTimeExam());
            responseList.add(examInfo);
        }

        return responseList;
    }

    private ListExamsStudentResponse getAllExams(List<Teacher> teacherList,List<Exam> examList) {
       ListExamsStudentResponse responseList = new ListExamsStudentResponse();
       for(Exam exam : examList){
           Teacher teacher = teacherList.stream().filter((p)->(p.getTeacherId()==exam.getTeacherId())).findFirst().get();
           ExamInfoStudentResponse examInfo = new ExamInfoStudentResponse(exam.getExamId(),exam.getName(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),exam.getQuestionCount(),exam.getTimeExam());
           responseList.add(examInfo);
       }

        return responseList;
    }



    private List<StudentQuestions> studentQuestionsList(List<Question> questionList){
        List<StudentQuestions> studentQuestionsList = new ArrayList<>();
        for(Question q : questionList){
            studentQuestionsList.add(new StudentQuestions(q));
        }
        return studentQuestionsList;
    }
    private List<AddQuestionsResponse> getList(List<Question> questionList){
        List<AddQuestionsResponse> listQuestion = new ArrayList<>();
        for(Question q: questionList){
            List<String> contentAnswers = new ArrayList<>();
            for(Answer a: q.getAnswerList()){
                contentAnswers.add(a.getContent());
            }
            listQuestion.add(new AddQuestionsResponse(q.getContent(),q.getQuestionId(),q.getQuestionNumber(),contentAnswers,q.getRightAnswer()));
        }
        return listQuestion;
    }
    private List<Question> generateRandomQuestions(Exam exam) {
        List <Question> buffer = new ArrayList<>(exam.getListQuestion());
        Collections.shuffle(buffer);
        return  buffer.subList(0,exam.getQuestionCount());
    }

    private Exam checkReadyState(int examId) throws ExamException {
        Exam exam = examDao.getExamById(examId);
        if(exam == null){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        if (exam.getStatus() != ExamStatus.READY) {
            throw new ExamException( ErrorCode.EXAM_IS_NOT_READY);
        }
        return exam;
    }


    private void checkStudentAnswers(Protocol protocol, List<Integer> answers){
        List<StudentQuestions> list = protocol.getStudentQuestions();

        int countAllAnswer = list.size();
        int countWrongAnswer = 0 ;
        int countCorrectAnswer = 0;
        int countWithoutAnswer = 0;
        for(int i = 0;i<list.size();i++){
            Integer answStud = answers.get(i);
            if(answStud == null){
                countWithoutAnswer++;
                list.get(i).setStudentAnswer(null);
                list.get(i).setStatus(AnswerStatus.NO_ANSWER);
            }else{

                Answer answer = list.get(i).getQuestion().getAnswerList().stream().filter((a)->a.getNumAnswer() == answStud).findFirst().orElse(null);
                if(answStud == list.get(i).getQuestion().getRightAnswer()){
                    countCorrectAnswer = countCorrectAnswer +1 ;
                    list.get(i).setStudentAnswer(answer);
                    list.get(i).setStatus(AnswerStatus.YES);

                }else{
                    countWrongAnswer++;
                    list.get(i).setStudentAnswer(answer);
                    list.get(i).setStatus(AnswerStatus.NO);
                }

            }
        }

        protocol.setStudentQuestions(list);
        protocol.setRightAnswerCount(countCorrectAnswer);
        protocol.setNoAnswerCount(countWithoutAnswer);
        protocol.setWrongAnswerCount(countWrongAnswer);

    }




}