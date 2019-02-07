package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.ExamStatus;
import net.thumbtack.netexam.model.Question;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.validator.FieldsValueMatch;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ExamMapper {
    @Insert("INSERT INTO exam (teacher_id,name,timeExam,semester,status,questionCount,showDetails) VALUES (#{exam.teacherId}," +
            "#{exam.name},#{exam.timeExam},#{exam.semester},#{exam.status},#{exam.questionCount},#{exam.showDetails})")
    @Options(useGeneratedKeys = true,keyProperty = "exam.examId")
    void insert(@Param("exam")Exam exam);

    @Select("SELECT * FROM exam  WHERE exam.id = #{examId}")
    @Results({
            @Result(property = "examId", column = "id"),
            @Result(property = "teacherId", column = "teacher_Id"),
            @Result(property = "listQuestion", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.QuestionMapper.getByExamId",fetchType = FetchType.EAGER))}
            )
    Exam getExamById(int examId);


    @Update("UPDATE exam SET timeExam=#{exam.timeExam},status =#{exam.status},questionCount=#{exam.questionCount},showDetails = #{exam.showDetails}, semester = #{exam.semester}, name = #{exam.name} where exam.id = #{exam.examId} ")
    void update(@Param("exam") Exam exam);

    @Select("SELECT COUNT(*) FROM protocol   WHERE   exam_id = #{examId}")
    int countExam(int examId);


    @Delete("DELETE FROM exam WHERE id = #{examId}")
    int delete(int examId);


   @Select("SELECT * FROM exam WHERE teacher_id = #{teacherId}")
    @Results({
            @Result(property = "examId", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "listQuestion",column = "id",javaType = List.class,
            many = @Many(select = "net.thumbtack.netexam.mappers.QuestionMapper.getByExamId",fetchType = FetchType.EAGER))
})
   List<Exam> getByTeacherId(@Param("teacherId") int teacherId);



    @Select("SELECT * FROM exam where semester =#{semester} and status = 'READY'")
    @Results({
            @Result(property = "examId", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "listQuestion",column = "id",javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.QuestionMapper.getByExamId",fetchType = FetchType.EAGER))

    })
    List<Exam> getExamBySemester(@Param("semester")int semester);

}
