package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Answer;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AnswerMapper {
    @Insert({"<script>",
            "INSERT INTO answer (content,number,question_id) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "(#{item.content},#{item.answerNumber} ,#{questionId})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true,keyProperty = "answerId")
    void insert(@Param("list")List<Answer> answers, @Param("questionId") int questionId);

    @Select("SELECT * FROM answer WHERE question_id = #{questionId}")
    @Results({
            @Result(property = "answerId", column = "id"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "answerNumber", column = "number")})
    List<Answer> getQuestionById(int questionId);

    @Select("SELECT * FROM answer WHERE id = #{id}")
    @Results({
            @Result(property = "questionId", column = "id")
    })
    Answer getById(int id);



}
