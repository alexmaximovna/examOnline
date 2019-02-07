package net.thumbtack.netexam.validator;

import net.thumbtack.netexam.utils.ConfigUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValid implements ConstraintValidator<PasswordConstraint, String> {
    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {

        return field != null && (field.length() >= (ConfigUtils.getInt("config.min_password_length"))) &&
                (field.length() <= (ConfigUtils.getInt("config.max_name_length")));

    }
}
