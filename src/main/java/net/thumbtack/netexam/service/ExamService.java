package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.ExamDaoImpl;
import net.thumbtack.netexam.exception.*;
import net.thumbtack.netexam.model.*;
import net.thumbtack.netexam.request.*;
import net.thumbtack.netexam.response.*;
import net.thumbtack.netexam.utils.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ExamService  extends ServiceBase{
    private static String notReady = "false";
    private static String ready = "true";

    public ExamInfoResponse addExam(AddExamRequest request, int teacherId) throws  ExamException {
        Exam exam = examDao.insert(new Exam(request.getName().toLowerCase(),Integer.parseInt(request.getSemester()),ExamStatus.NOT_READY,teacherId));
        return new ExamInfoResponse(exam.getExamId(),exam.getName(),exam.getSemester());
    }

    public ExamInfoResponse updateExam(AddExamRequest request, User user, int examId) throws   ExamException {
        checkUserIsNotTeacher(user);
        Exam exam = examDao.getExamById(examId);
        if (exam == null || exam.getStatus().toString().equals("READY")) {
            throw new ExamException(ErrorCode.EXAM_NOT_UPDATE);
        }
        String semester = request.getSemester();
        String name = request.getName();
        Exam examUpdate;
        if (semester== null || StringUtils.isEmpty(semester) && (name != null || !StringUtils.isEmpty(name))) {
            examUpdate = examDao.update(new Exam(exam.getExamId(), request.getName().toLowerCase(), exam.getSemester(),ExamStatus.NOT_READY));


        }else {

            examUpdate = examDao.update(new Exam(exam.getExamId(), exam.getName().toLowerCase(), Integer.parseInt(semester), ExamStatus.NOT_READY));
        }return new ExamInfoResponse(examUpdate.getExamId(), examUpdate.getName(), examUpdate.getSemester());

    }


    public BaseResponseObject deleteExam(User user, int examId) throws  ExamException, DataBaseException {
        checkUserIsNotTeacher(user);

        Protocol protocol = protocolDao.getProtocol(examDao.getExamById(examId));
        if(protocol != null) {
           if (protocol.getStartTime().getTime() + TimeUnit.MINUTES.toMillis(protocol.getExam().getTimeExam()) < new Date().getTime()) {
                throw new ExamException(ErrorCode.EXAM_PASS_AT_THE_MOMENT);
            }
        }

        if(examDao.countExams(examId)!=0){
            throw new ExamException();
        }
        examDao.deleteExam(examId);
        return new BaseResponseObject();
    }


    public AddSetQuestionsResponse addQuestions(int examId, User user, AddSetQuestionsRequest request) throws ExamException, DataBaseException {
        checkUserIsNotTeacher(user);

        Exam exam = examDao.getExamById(examId);
        if(exam == null){
            throw new ExamException(ErrorCode.EXAM_IS_NOT_EXIST);
        }
        if (exam.getStatus() != ExamStatus.NOT_READY) {
            throw new ExamException( ErrorCode.EXAM_IS_READY);
        }
        List<Question> questions = new ArrayList<>();

        for (AddQuestionRequest questionRequest: request.getAddQuestionRequestList()) {

            if(questionRequest.getNumber()< 0){
                throw new ExamException(ErrorCode.NOT_CORRECT_NUMBER_QUESTION);
            }
            if(questionRequest.getQuestion() == null){
                throw new ExamException(ErrorCode.EMPTY_QUESTION_BODY);
            }
            Question question =  new Question(questionRequest.getQuestion(),
                    (questionRequest.getNumber() == null ? 0 : questionRequest.getNumber()),
                    (questionRequest.getCorrect()== null ? 0 : questionRequest.getCorrect()), examId);

            if (questionRequest.getAnswers() != null && questionRequest.getAnswers().size() != 0) {
                for (int i = 0; i < questionRequest.getAnswers().size(); i++) {

                    question.addAnswer(new Answer(question.getQuestionId(),questionRequest.getAnswers().get(i),i));

                }
                questions.add(question);
            }
        }


        List<Question> questionsFromDataBase = examDao.addQuestions(questions, examId);

       List<AddQuestionsResponse> questionsResponses = new ArrayList<>();
        for (Question question: questionsFromDataBase) {
            List<String> answers = new ArrayList<>();
            for (Answer answer: question.getAnswerList()) {

                answers.add(answer.getContent());
            }
            AddQuestionsResponse response = new AddQuestionsResponse(question.getContent(), question.getQuestionId(), question.getQuestionNumber(), answers, question.getRightAnswer());
            questionsResponses.add(response);
        }
        return new AddSetQuestionsResponse(questionsResponses);
    }

    public StateReadyResponse changeStatus(int examId, User user, StateReadyRequest request) throws ExamException, DataBaseException {
        ConfigUtils configUtils = new ConfigUtils();

        int countQuestionRequest = request.getQuestionsCountPerExam();
        checkUserIsNotTeacher(user);
        Exam exam = examDao.getExamById(examId);

        if(exam == null){
            throw new ExamException(ErrorCode.EXAM_IS_NOT_EXIST);
        }
        if (exam.getStatus() == ExamStatus.READY) {
            throw new ExamException( ErrorCode.EXAM_IS_ALREADY_READY);
        }
        List<Question> questionListDB = examDao.getListQuestions(examId);

        if(countQuestionRequest < ConfigUtils.getInt("config.min_questions_count_per_exam")||questionListDB.size()<countQuestionRequest){
            throw new ExamException(ErrorCode.OVERFLOW_MIN_COUNT_QUESTIONS);
        }
        if(request.getTimeInMinutes()< ConfigUtils.getInt("config.min_time")){
            throw new ExamException(ErrorCode.OVERFLOW_MIN_TIME);
        }
        Set<Integer> setNumberQuestion = new HashSet<>();

        for(Question question : questionListDB){

            if(question.getContent() == null || StringUtils.isEmpty(question.getContent())){
                throw new ExamException(ErrorCode.EMPTY_QUESTION_BODY);
            }

            if(question.getAnswerList().size()<ConfigUtils.getInt("config.min_answers")){
                throw new ExamException(ErrorCode.OVERFLOW_MIN_COUNT_ANSWERS);
            }
            //check answer
            if(question.getRightAnswer() != 0){
                List<Integer> listNumAnswer = new ArrayList<>();
                for(Answer answer : question.getAnswerList()){
                    if(answer == null || StringUtils.isEmpty(answer.getContent())){
                        throw  new ExamException(ErrorCode.EMPTY_ANSWER);
                    }
                    listNumAnswer.add(answer.getNumAnswer());

                }


                if(!listNumAnswer.contains(question.getRightAnswer())){
                    throw new ExamException(ErrorCode.NOT_EXITS_CORRECT_NUMBER_ANSWER);
                }
            }else{
                throw new ExamException(ErrorCode.NOT_SPECIFIED_CORRECT_ANSWER);
            }
            //end check answer

            setNumberQuestion.add(question.getQuestionNumber());

        }

        if(setNumberQuestion.contains(0) || setNumberQuestion.size()<questionListDB.size()){
            throw new ExamException(ErrorCode.NOT_CORRECT_NUMBER_QUESTION);
        }
        examDao.update(new Exam(examId,exam.getName(),request.getTimeInMinutes(),exam.getTeacherId(),exam.getSemester(),ExamStatus.READY,request.getQuestionsCountPerExam(),request.getShowDetails(),questionListDB));
        return new StateReadyResponse(request.getQuestionsCountPerExam(),request.getTimeInMinutes(),request.getShowDetails());
    }

    public CopyExamResponse copyExam(User user, CopyExamRequest request) throws ExamException {
        checkUserIsNotTeacher(user);
        Exam examDB = examDao.getExamById(request.getSourceId());
        if(examDB == null){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        Exam copyExam;
        if(examDB.getListQuestion() == null ||examDB.getListQuestion().size()==0 ){

          copyExam = examDao.insert(new Exam(request.getName().toLowerCase(),examDB.getTeacherId(),request.getSemester(),ExamStatus.NOT_READY));
        }else {

          copyExam = examDao.insertCopy(new Exam(examDB.getTeacherId(), request.getName().toLowerCase(), request.getSemester(), ExamStatus.NOT_READY, examDB.getListQuestion()));
        }
        return new CopyExamResponse(copyExam.getExamId(),copyExam.getName(),copyExam.getSemester());
    }

    public AddSetQuestionsResponse getQuestions(User user, int examId) throws ExamException, DataBaseException {
        checkUserIsNotTeacher(user);

        List<Question> questionsFromDataBase = examDao.getListQuestions(examId);
        List<AddQuestionsResponse> questionsResponses = new ArrayList<>();
        for (Question question: questionsFromDataBase) {
            List<String> answers = new ArrayList<>();
            for (Answer answer: question.getAnswerList()) {

                answers.add(answer.getContent());
            }
            AddQuestionsResponse response = new AddQuestionsResponse(question.getContent(), question.getQuestionId(), question.getQuestionNumber(), answers, question.getRightAnswer());
            questionsResponses.add(response);
        }
        return new AddSetQuestionsResponse(questionsResponses);
    }

    public ParametersExamResponse getParamExam(User user, int examId) throws ExamException {
        checkUserIsNotTeacher(user);

        Exam examDB = examDao.getExamById(examId);
        if(examDB == null){
            throw  new ExamException(ErrorCode.NOT_FOUND);
        }
        if(examDB.getStatus()== ExamStatus.NOT_READY){
            return new ParametersExamResponse(examDB.getName().toLowerCase(),examDB.getExamId(),examDB.getSemester(),notReady);
        }else{
            return new ParametersExamResponse(examDB.getName().toLowerCase(),examDB.getExamId(),examDB.getSemester(),ready,examDB.getQuestionCount(),examDB.getTimeExam(),examDB.getShowDetails());
        }

    }


    public SetParametersExamsResponse getParamExams(User user, String name, Integer semester, Boolean complete) throws DataBaseException, ExamException {
        checkUserIsNotTeacher(user);
        int teacherId = userDao.getTeacherId(user.getUserId());
        ExamStatus examStatus =(complete == null?  null :(complete ?  ExamStatus.READY : ExamStatus.NOT_READY));
        int sem = (semester==null? 0: semester);
        SetParametersExamsResponse setParametersExams = new SetParametersExamsResponse();

        List<Exam> examList = examDao.getListExam(teacherId,name,sem,examStatus);
        for(Exam exam : examList){
            ParametersExamResponse examResponse;
            if(exam.getStatus() == ExamStatus.NOT_READY){
                examResponse = new ParametersExamResponse(exam.getName(),exam.getExamId(),exam.getSemester(),notReady,0,0,null) ;
            }else {
                examResponse = new ParametersExamResponse(exam.getName(), exam.getExamId(), exam.getSemester(), ready, exam.getQuestionCount(), exam.getTimeExam(), exam.getShowDetails());
            }

            setParametersExams.add(examResponse);

        }
        return setParametersExams;
    }

    public StudentsPassExamResponse getListStudentPassExam(User user, int examId) throws DataBaseException, ExamException {
        checkUserIsNotTeacher(user);
        List<Protocol> protocolList = protocolDao.getListProtocol(examId);
        if(protocolList == null){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        List<Protocol> newPr = protocolList.stream().filter((p)->(TimeUnit.MINUTES.toMillis((p.getExam().getTimeExam()))+p.getStartTime().getTime())>=new Date().getTime()).collect(Collectors.toList());;
        if(newPr.size()==0){
            throw new ExamException(ErrorCode.NOT_FOUND);
        }
        String examName = protocolList.get(0).getExam().getName();
        List<GroupsResponse> groupList = new ArrayList<>();
        List<String> groupNameList = newPr.stream().map((p)->(p.getStudent().getGroup())).distinct().collect(Collectors.toList());
        for(String nameGroup :groupNameList ){
            List<Student> studentList = newPr.stream().filter((p)->(p.getStudent().getGroup().equals(nameGroup))).map((r)->(r.getStudent())).collect(Collectors.toList());
            GroupsResponse groups = new GroupsResponse();
            groups.setNameGroup(nameGroup);
            List<StudentsResponse> studentsList = new ArrayList<>();
            for(Student student : studentList){
               Protocol protocolByStudentId = newPr.stream().filter((p)->(p.getStudent().getStudentId() == student.getStudentId())).findAny().get();
               StudentsResponse students = new StudentsResponse(student.getFirstName(),student.getLastName(),student.getPatronymic(),
                       protocolByStudentId.getRightAnswerCount(),protocolByStudentId.getWrongAnswerCount(),protocolByStudentId.getNoAnswerCount());
               studentsList.add(students);

            }
            groups.setStudentList(studentsList);
            groupList.add(groups);
        }

        return new StudentsPassExamResponse(examName,groupList);

    }

}


