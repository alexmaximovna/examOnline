package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.*;
import net.thumbtack.netexam.validator.FieldsValueMatch;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ProtocolMapper {
    @Select("SELECT COUNT(*) FROM protocol JOIN `student` ON  user_id =#{userId} " +
            "WHERE protocol.student_id = student.id")
    int getCountExams(int userId);


   @Select("Select * from protocol where exam_id =#{examId}")
   @Results({
       @Result(property = "protocolId", column = "id"),
           @Result(property = "student", column = "student_id", javaType = Student.class,
               one = @One(select = "net.thumbtack.netexam.mappers.StudentMapper.getById",fetchType = FetchType.EAGER)),
           @Result(property = "exam", column = "exam_id", javaType = Exam.class,
                   one = @One(select = "net.thumbtack.netexam.mappers.ExamMapper.getExamById",fetchType = FetchType.EAGER))
   })
   List<Protocol> getProtocolList(int examId);



    @Select("SELECT * FROM protocol WHERE student_id = #{studentId}")
    @Results({
            @Result(property = "protocolId", column = "id"),
            @Result(property = "student", column = "student_id", javaType = Student.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.StudentMapper.getById",fetchType = FetchType.EAGER)),
            @Result(property = "exam", column = "exam_id", javaType = Exam.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.ExamMapper.getExamById",fetchType = FetchType.EAGER)),
            @Result(property = "studentQuestions", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.ProtocolMapper.getQuestionsAnswerByProtocolId",fetchType = FetchType.EAGER))
    })
    List<Protocol> getProtocolById(@Param("studentId")int studentId);



    @Insert("Insert INTO protocol(student_id,exam_id,startTime) VALUES ( #{student.studentId},#{exam.examId}, #{startTime})")
            @Options(useGeneratedKeys = true, keyProperty = "protocol.protocolId")
    void insert(@Param("exam") Exam exam,@Param("student") Student student,
                    @Param("startTime")String startTime, @Param("protocol")Protocol protocol);

    @Insert({"<script>",
            "INSERT INTO answers_student (question_id, protocol_id) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.question.questionId}, #{protocolId} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void insertQuestions(@Param("list") List<StudentQuestions> questions, @Param("protocolId") int id);





    @Select("SELECT * FROM protocol WHERE student_id = #{studentId} and exam_id = #{examId}")
    @Results({
            @Result(property = "protocolId", column = "id"),
            @Result(property = "exam", column = "exam_id", javaType = Exam.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.ExamMapper.getExamById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "student", column = "student_id", javaType = Student.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.StudentMapper.getById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "studentQuestions",column = "id",javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.ProtocolMapper.getQuestionsAnswerByProtocolId",
                            fetchType = FetchType.LAZY))

    })
    Protocol getProtocol(@Param("studentId") int studentId, @Param("examId") int examId);



    @Select("SELECT * FROM answers_student WHERE protocol_id = #{protocolId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "status",column = "status"),
            @Result(property = "question", column = "question_id", javaType = Question.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.QuestionMapper.getById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "studentAnswer", column = "answer_id", javaType = Answer.class,
                            one = @One(select = "net.thumbtack.netexam.mappers.AnswerMapper.getById",
                                    fetchType = FetchType.LAZY))
    })
    @Options(useGeneratedKeys = true)
    List<StudentQuestions> getQuestionsAnswerByProtocolId(int protocolId);



    @Update({"UPDATE protocol SET finishTime = #{protocol.finishTime}, rightAnswerCount = #{protocol.rightAnswerCount}," +
            "wrongAnswerCount =#{protocol.wrongAnswerCount},noAnswerCount =#{protocol.noAnswerCount} " +
            "WHERE id = #{protocol.protocolId} "})
    void update(@Param("protocol")Protocol protocol);




    @Insert({"<script>",
            "INSERT INTO answers_student (question_id,answer_id,protocol_id,status) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.question.questionId},#{item.studentAnswer.answerId}, #{protocolId}, #{item.status} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void insertAnswers(@Param("list")List<StudentQuestions> studentQuestions,@Param("protocolId") int protocolId);



    @Select("SELECT * FROM protocol WHERE exam_id = #{exam.examId}")
    @Results({
            @Result(property = "protocolId", column = "id"),
            @Result(property = "exam", column = "exam_id", javaType = Exam.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.ExamMapper.getById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "student", column = "student_id", javaType = Student.class,
                    one = @One(select = "net.thumbtack.netexam.mappers.StudentMapper.getById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "studentQuestions",column = "id",javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.ProtocolMapper.getQuestionsAnswerByProtocolId",
                            fetchType = FetchType.LAZY))

    })
    Protocol getProt(@Param("exam") Exam exam);
}
