package net.thumbtack.netexam.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String fieldValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(field);
        String fieldMatchValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        if(fieldValue != null&& fieldMatchValue != null&&!fieldValue.equals("") &&  !fieldMatchValue.equals("")){
            return Integer.parseInt(fieldMatchValue) >= 1 && Integer.parseInt(fieldMatchValue) <= 12;

        }else {
            return (fieldMatchValue != null && !fieldMatchValue.equals("")) || (field != null && !field.equals(""));
        }
    }
}

