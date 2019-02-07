package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.exception.AuthenticationException;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.exception.ErrorCode;
import net.thumbtack.netexam.exception.ExamException;
import net.thumbtack.netexam.response.JsonErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private String message = "Value %s is unacceptable for parameter ";
    private String notCorrectPair = "Error with exam name or/and semester";


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    public JsonErrorResponse handleDataBaseError(DataBaseException ex) {
        return new JsonErrorResponse();

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleProblemLogIn(AuthenticationException ex) {
        ErrorCode code = ex.getErrorCode();
        return new ResponseEntity<>(new JsonErrorResponse(code, code.getField(), code.getErrorCode(ex)), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {

        if (ex.getMessage().contains(notCorrectPair)) {
            return new ResponseEntity<>(new JsonErrorResponse(ErrorCode.NOT_CORRECT_DATA, "",
                    notCorrectPair), new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }


        List<JsonErrorResponse> list = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldError error = fieldError;
            String nameField = fieldError.getField();
            list.add(new JsonErrorResponse(ErrorCode.getCode(nameField), nameField,
                    String.format(message, error.getRejectedValue()) + nameField));

        }


        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ExamException.class)
    public ResponseEntity<?> handleProblemExam(ExamException ex) {
        ErrorCode code = ex.getErrorCode();
        return new ResponseEntity<>(new JsonErrorResponse(code, code.getField(), code.getErrorCode()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}
