package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Student;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.model.UserType;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Insert("INSERT INTO `user`(firstname,lastname,patronymic,login,password,user_type) VALUES " +
            "( #{user.firstName}, #{user.lastName},#{user.patronymic}," +
            "#{user.login},#{user.password},#{user.userType})")
    @Options(useGeneratedKeys = true, keyProperty = "user.userId")
    Integer insert(@Param("user") User user, @Param("userType") UserType userType);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    @Results({
            @Result(property = "userId", column = "id"),
            @Result(property = "userType", column = "user_type")})
    User getById(@Param("id")int id);

    @Delete("DELETE FROM `user`")
    void deleteAll();

    @Select("SELECT * FROM `user` WHERE login = #{login}")
    @Results({
            @Result(property = "userId", column = "id"),
            @Result(property = "userType", column = "user_type")})
    User getUserByLogin(String login);


    @Select("SELECT id FROM `user` WHERE login = #{login}")
    int getUserIdByLogin(String login);


    @Select("SELECT * from `user` WHERE login = #{login} AND password = #{password} ")
    @Results({
            @Result(property = "userId", column = "id"),
            @Result(property = "userType", column = "user_type")})
    User getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Update("UPDATE `user` SET  firstname =#{user.firstName} ,lastname = #{user.lastName} ,patronymic = #{user.patronymic}," +
            "password = #{user.password} where id =#{user.userId} ")
    void update(@Param("user") User user,@Param("userType") UserType userType);


    @Select("Select teacher.id from teacher JOIN `user` on `user`.id = teacher.user_id where user.id = #{userId}")
    int getTeacherIdByUserId(int userId);

}





