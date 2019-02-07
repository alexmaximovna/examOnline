package net.thumbtack.netexam.validator;

import net.thumbtack.netexam.utils.ConfigUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValid implements ConstraintValidator<LoginConstraint, String> {

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {

        return field != null && field.matches("|^[A-Za-zА-Яа-я0-9]+") && (field.length() <= ConfigUtils.getInt("config.max_name_length"));
    }
}
