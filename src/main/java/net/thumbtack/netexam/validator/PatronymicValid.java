package net.thumbtack.netexam.validator;

import net.thumbtack.netexam.utils.ConfigUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatronymicValid implements ConstraintValidator<PatronymicConstraint, String> {
    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        if (field == null || field.length() == 0) {
            return true;
        }
        return field.matches("|^[А-Яа-я- ]+") && (field.length() <= (ConfigUtils.getInt("config.max_name_length")));

    }
}
