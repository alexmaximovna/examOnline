package net.thumbtack.netexam.mappers;

import net.thumbtack.netexam.model.Exam;
import net.thumbtack.netexam.model.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface QuestionMapper {
    @Insert({"<script>",
            "INSERT INTO question (content,number,rightAnswer,exam_id) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.content},#{item.questionNumber}, #{item.rightAnswer}, #{examId})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    void insert(@Param("list")List<Question> questions, @Param("examId") int examId);

    @Update({"<script>",
            " Update question SET (content,number,rightAnswer,exam_id) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.content},#{item.questionNumber}, #{item.rightAnswer}, #{examId})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    void update(@Param("list")List<Question> questions, @Param("examId") int examId);

    @Select("SELECT * FROM question WHERE id = #{id}")
    @Results({
            @Result(property = "questionNumber", column = "number"),
            @Result(property = "questionId", column = "id"),
            @Result(property = "answerList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.netexam.mappers.AnswerMapper.getQuestionById",fetchType = FetchType.EAGER))
    })
    Question getById(int id);

    @Delete("DELETE FROM question")
    void deleteAll();

    @Select("SELECT * FROM question WHERE exam_id = #{examId}")
    @Results({
            @Result(property = "questionId", column = "id"),
            @Result(property = "questionNumber", column = "number"),
            @Result(property = "answerList", column = "id", javaType = List.class,
               many = @Many(select = "net.thumbtack.netexam.mappers.AnswerMapper.getQuestionById",fetchType = FetchType.EAGER))
    })
    List<Question> getByExamId(int examId);


}
