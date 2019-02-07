package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.ExamStatus;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TeacherMapper {

    @Insert("INSERT INTO teacher (department,position,user_id) VALUES " +
            "( #{teacher.department}, #{teacher.position}, #{teacher.userId})")
    @Options(useGeneratedKeys = true,keyProperty = "teacher.teacherId")
    void insert( @Param ("teacher") Teacher teacher);

    @Select("SELECT * FROM `user` JOIN teacher ON teacher.user_id = user.id WHERE user.id = #{id}")
    @Results({
            @Result(property = "teacherId", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userType", column = "user_type")})
    Teacher getByUserId(int userId);




    @Delete("DELETE FROM teacher")
    void deleteAll();


    @Update("UPDATE teacher SET department = #{teacher.department}, position = #{teacher.position} where user_id = #{teacher.userId} ")
    void update(@Param("teacher") Teacher teacher);




    @Select("SELECT * FROM teacher JOIN `user` ON teacher.user_id = user.id WHERE teacher.id = #{id}")
    @Results({
            @Result(property = "teacherId", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userType", column = "user_type"),
            @Result(property = "examsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.ExamMapper.getByTeacherId",
                            fetchType = FetchType.LAZY))

    })
    Teacher getById(int id);



    @Select({"<script>",
            "SELECT * FROM exam",
            "<where>" +
                    "<if test='name != null'> AND name = #{name}",
            "</if>",
            "<if test='semester != 0'> AND semester = #{semester}",
            "</if>",
            "<if test='status != null'> AND status = #{status}",
            "</if>",
            "AND teacher_id = #{teacherId}",
            "</where>" +
                    "</script>"})
    @Results({
            @Result(property = "examId", column = "id")
    })
    List<Exam> getExamList(@Param("teacherId") int id, @Param("name") String name,
                           @Param("semester") int semester, @Param("status") ExamStatus status);

    @Select("SELECT teacher.id as teacherId,department,position,user_id,user.id as userId,firstname,lastname,patronymic,login,password,user_type FROM teacher JOIN `user` ON teacher.user_id = user.id")
    @Results({
            @Result(property = "teacherId", column = "teacherId"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userType", column = "user_type"),
            @Result(property = "examList", column = "teacherId", javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.ExamMapper.getByTeacherId",
                            fetchType = FetchType.EAGER))

    })
    List<Teacher> getAll();
}
