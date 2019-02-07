package net.thumbtack.netexam.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PatronymicValid.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface PatronymicConstraint {
    String message() default "Invalid patronymic";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
