package net.thumbtack.netexam.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SemesterValid implements ConstraintValidator<SemesterConstraint, Integer> {
    @Override
    public boolean isValid(Integer field, ConstraintValidatorContext constraintValidatorContext) {
        return field <= 12 && field >= 1;
    }
}
