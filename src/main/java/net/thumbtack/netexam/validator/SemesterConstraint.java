package net.thumbtack.netexam.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SemesterValid.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SemesterConstraint {
    String message() default "Invalid semester";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
