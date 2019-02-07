package net.thumbtack.netexam.validator;

import net.thumbtack.netexam.utils.ConfigUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonalNameValid  implements ConstraintValidator<PersonalNameConstraint,String> {


    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {

        return field!= null && field.length()!=0&& field.matches("|^[А-Яа-я- ]+")&& (field.length()<= (ConfigUtils.getInt("config.max_name_length")));
    }
}
