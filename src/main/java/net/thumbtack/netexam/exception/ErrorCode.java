package net.thumbtack.netexam.exception;

public enum ErrorCode {
    AUTHENTICATION_FAILED("login or password", "Authentication is failed,wrong login or password"),
    LOGIN_ALREADY_EXIST("login", "Login %s already exists"),
    SERVER_ERROR("Server error"),
    PARAM_INVAILD("Parameter is invalid"),
    INVALID_FIRSTNAME("firstname", "firstname"),
    INVALID_LASTNAME("lastname", "lastname"),
    INVALID_PATRONYMIC("patronymic", "patronymic"),
    INVALID_LOGIN("login", "login"),
    INVALID_PASSWORD("password", "password"),
    INVALID_SEMESTER_NUMBER("semester", "semester"),
    INVALID_GROUP("group", "group"),
    INVALID_POSITION("position", "position"),
    INVALID_DEPARTMENT("department", "department"),
    WRONG_OLD_PASSWORD("password", "Passwords do not match"),
    USER_IS_NOT_TEACHER("User isn't teacher"),
    USER_IS_NOT_STUDENT("User isn't student"),
    EXAM_EXIST_IN_SEMESTER("Exam exists int this semester"),
    EXAM_NOT_UPDATE("Exam don't update"),
    EXAM_ALREADY_PASS("This exam already passed"),
    NOT_CORRECT_DATA("Not correct data"),
    EXAM_IS_READY("Exam is ready"),
    EXAM_IS_NOT_EXIST("This exam not exists"),
    NOT_CORRECT_NUMBER_QUESTION("Number question must be positive"),
    EXAM_IS_ALREADY_READY("Exam has got state READY"),
    OVERFLOW_MIN_COUNT_QUESTIONS("Overflow min count questions"),
    OVERFLOW_MIN_TIME("Overflow min time"),
    EMPTY_QUESTION_BODY("Empty question body"),
    OVERFLOW_MIN_COUNT_ANSWERS("Overflow min count answers"),
    NOT_SPECIFIED_CORRECT_ANSWER("Not specified correct answer"),
    NOT_EXITS_CORRECT_NUMBER_ANSWER("Not exists correct number answer"),
    EMPTY_ANSWER("Empty answer"),
    NOT_FOUND("Not found"),
    THERE_ARE_NOT_PROTOCOLS("There are not protocols"),
    EXAM_IS_NOT_READY("Exam is not ready"),
    EXAM_IS_PASSED("Exam already passed"),
    EXAM_PASS_AT_THE_MOMENT("Exam is passing this moment"),
    NULL_COOKIE("Null cookie"),
    EXAM_NOT_STUDENT_SEMESTER("Student can't pass exam in not its semester"),
    NULL_SESSION("Null session");



    private String errorCode;
    private String field;


    ErrorCode(String errorCode) {
        this.errorCode = errorCode;

    }

    ErrorCode(String field, String errorCode) {
        this.field = field;
        this.errorCode = errorCode;

    }


    public static ErrorCode getCode(String errorCode) {
        for (ErrorCode code : ErrorCode.values()) {
            if (code.errorCode.equalsIgnoreCase(errorCode)) {
                return code;
            }
        }
        return null;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorCode(AuthenticationException ex) {
        if (ex.getCurrentValue() != null && !ex.getCurrentValue().equals("")) {
            return String.format(this.errorCode, ex.getCurrentValue());
        }
        return errorCode;
    }

    public String getField() {
        return field;
    }


}
