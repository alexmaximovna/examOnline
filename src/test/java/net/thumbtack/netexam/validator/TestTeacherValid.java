package net.thumbtack.netexam.validator;


import net.thumbtack.netexam.request.RegisterTeacherDtoRequest;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestTeacherValid {
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }


    @Test
    public void testAllGood()  {
        RegisterTeacherDtoRequest dto = new RegisterTeacherDtoRequest("Иванов",
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "abc","abc");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterTeacherDtoRequest>> constrains = validator.validate(dto);
        Assert.assertEquals(0, constrains.size());

    }

    @Test
    public void testNullFirstName()  {
        RegisterTeacherDtoRequest dto = new RegisterTeacherDtoRequest(null,
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "abc","abc");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterTeacherDtoRequest>> constrains = validator.validate(dto);
        assertEquals(1, constrains.size());

        for (ConstraintViolation<RegisterTeacherDtoRequest> constrain : constrains) {
            assertEquals("firstName",constrain.getPropertyPath().toString());
            assertEquals("Invalid name",constrain.getMessage());
        }
    }
    @Test
    public void testNullPatronymic()  {
        RegisterTeacherDtoRequest dto = new RegisterTeacherDtoRequest("Иванов",
                "Иван",null,"kdfgkgg","jdjvf554",
                "abc","abc");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterTeacherDtoRequest>> constrains = validator.validate(dto);
        assertEquals(0, constrains.size());

    }

    @Test
    public void testFailLoginAndPassword()  {
        RegisterTeacherDtoRequest dto = new RegisterTeacherDtoRequest("Иван",
                "Иван","Иванович","kdfgkgg+*","jdj",
                "abc","abc");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterTeacherDtoRequest>> constrains = validator.validate(dto);
        assertEquals(2, constrains.size());
        List<String> list = new ArrayList<>();
        List<String> listRes = new ArrayList<>();
        list.add("Invalid password");
        list.add("Invalid login");
        for (ConstraintViolation<RegisterTeacherDtoRequest> constrain : constrains) {
            listRes.add(constrain.getMessage());
        }
        assertTrue(list.containsAll(listRes));
    }
    @Test
    public void testFailPositionAndDepartment()  {
        RegisterTeacherDtoRequest dto = new RegisterTeacherDtoRequest("Иван",
                "Иван","Иванович","kdfgkgg","jdjggggg",
                "",null);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterTeacherDtoRequest>> constrains = validator.validate(dto);
        assertEquals(2, constrains.size());
        List<String> list = new ArrayList<>();
        List<String> listRes = new ArrayList<>();
        list.add("position");
        list.add("department");
        for (ConstraintViolation<RegisterTeacherDtoRequest> constrain : constrains) {

            listRes.add(constrain.getPropertyPath().toString());
        }
        assertTrue(list.containsAll(listRes));
    }
}
