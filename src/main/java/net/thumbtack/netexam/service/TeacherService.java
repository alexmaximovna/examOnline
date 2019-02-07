package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.TeacherDaoImpl;
import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.model.Teacher;
import net.thumbtack.netexam.model.User;
import net.thumbtack.netexam.model.UserType;
import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import net.thumbtack.netexam.request.UpdateInfoTeacherDtoRequest;
import net.thumbtack.netexam.response.TeacherInfoDtoResponse;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends ServiceBase {

    public TeacherInfoDtoResponse register(RegisterTeacherDtoRequest dto) throws DataBaseException, AuthenticationException, ExamException {
        Teacher teacher = teacherDao.insert(new Teacher(dto.getFirstName(),dto.getLastName(),dto.getPatronymic(),
                        dto.getLogin().toLowerCase() ,dto.getPassword() ,UserType.TEACHER,dto.getDepartment(),dto.getPosition()));
        checkUser(teacher);
        return  new TeacherInfoDtoResponse(teacher.getFirstName(),teacher.getLastName(),
              teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),teacher.getUserType());
    }

    public TeacherInfoDtoResponse update(UpdateInfoTeacherDtoRequest dto,User user) throws  AuthenticationException, ExamException {
        checkUserIsNotTeacher(user);
        if(!user.getPassword().equals(dto.getOldPassword())){
                throw new AuthenticationException(ErrorCode.WRONG_OLD_PASSWORD);
            }

        Teacher teacher = teacherDao.update(new Teacher(user.getUserId(),dto.getFirstName(),dto.getLastName(),dto.getPatronymic(),
                user.getLogin(),dto.getNewPassword() ,UserType.TEACHER,dto.getDepartment(),dto.getPosition()));
        checkUser(teacher);
        return  new TeacherInfoDtoResponse(teacher.getFirstName(),teacher.getLastName(),
                teacher.getPatronymic(),teacher.getDepartment(),teacher.getPosition(),teacher.getUserType());
    }


}
