package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.User;
import org.apache.ibatis.annotations.*;

public interface SessionMapper {
    @Insert("INSERT INTO `session` (cookies,user_id) VALUES " +
            "( #{cookies}, #{userId}) ON DUPLICATE KEY UPDATE cookies = #{cookies}")
    @Options(useGeneratedKeys = true)
    void insert( @Param("cookies") String cookies,@Param("userId") int userId);

    @Delete("DELETE FROM `session` WHERE cookies = cookies ")
    void delete(String cookies);

    @Select("SELECT * FROM `session`  WHERE cookies = #{cookies}")
    int getUserIdByCookies(String cookies);

    @Delete("DELETE FROM `session`")
    void deleteAll();

    @Select("SELECT * FROM `session` JOIN `user` ON user.id = session.user_id WHERE cookies = #{cookie}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userType", column = "user_type")})
    User getUserByCookies(String cookie);

    @Select("Select teacher.id from teacher JOIN `session` on session.cookies = #{cookie} JOIN `user` on session.user_id = user.id")
    int getTeacherId(String cookie);

}
