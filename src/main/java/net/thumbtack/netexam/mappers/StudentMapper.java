package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface StudentMapper {
    @Insert("INSERT INTO student (`group`,semester,user_id) VALUES " +
            "( #{student.group}, #{student.semester}, #{student.userId})")
    @Options(useGeneratedKeys = true,keyProperty = "student.studentId")
    void insert( @Param("student") Student student);


    @Select("SELECT `user`.id as usId,firstname,lastname,login,password,user_type,student.id,`group`,semester,user_id " +
            "FROM `user` JOIN  student ON  student.user_id = `user`.id WHERE `user`.id =  #{userId}")
    @Results({
            @Result(property = "studentId", column = "id"),
            @Result(property = "userId", column = "usId"),
            @Result(property = "userType", column = "user_type")})
    Student getByUserId(int userId);

    @Select("SELECT student.id as stId,`group`,semester,user_id,user.id,firstname,lastname,login,password,user_type FROM student JOIN `user` ON student.user_id = user.id WHERE student.id = #{id}")
    @Results({

            @Result(property = "studentId", column = "stId"),
            @Result(property = "userType", column = "user_type"),
            @Result(property = "userId", column = "user_id" )})
    Student getById(int id);

    @Delete("DELETE FROM student")
    void deleteAll();

    @Update("UPDATE student SET `group` = #{student.group}, semester = #{student.semester} where user_id = #{student.userId}")
    void updateAll(@Param("student") Student student);

    @Select("Select `group`,semester from student where id = #{student.studentId}")
    @Results({
            @Result(property = "studentId", column = "id"),
            @Result(property = "group", column = "group"),
            @Result(property = "semester", column = "semester")})
    Student update(@Param("student") Student student);
}
