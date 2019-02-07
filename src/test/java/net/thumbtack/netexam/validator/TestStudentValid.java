package net.thumbtack.netexam.validator;

import net.thumbtack.netexam.request.RegisterStudentDtoRequest;
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

public class TestStudentValid {
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }


    @Test
    public void testAllGood()  {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иванов",
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "1",1);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterStudentDtoRequest>> constrains = validator.validate(dto);

        Assert.assertEquals(0, constrains.size());

    }

    @Test
    public void testNullFirstName()  {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest(null,
                "Иван","Иванович","kdfgkgg","jdjvf554",
                "1",1);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterStudentDtoRequest>> constrains = validator.validate(dto);
        assertEquals(1, constrains.size());

        for (ConstraintViolation<RegisterStudentDtoRequest> constrain : constrains) {
            assertEquals("firstName",constrain.getPropertyPath().toString());
            assertEquals("Invalid name",constrain.getMessage());
        }
    }
    @Test
    public void testNullPatronymic()  {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иванов",
                "Иван",null,"kdfgkgg","jdjvf554",
                "1",1);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterStudentDtoRequest>> constrains = validator.validate(dto);
        assertEquals(0, constrains.size());

    }

    @Test
    public void testFailLoginAndPassword()  {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иван",
                "Иван","Иванович","kdfgkgg+*","jdj",
                "1",1);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterStudentDtoRequest>> constrains = validator.validate(dto);
        assertEquals(2, constrains.size());
        List<String> list = new ArrayList<>();
        List<String> listRes = new ArrayList<>();
        list.add("Invalid password");
        list.add("Invalid login");
        for (ConstraintViolation<RegisterStudentDtoRequest> constrain : constrains) {
            listRes.add(constrain.getMessage());
        }
        assertTrue(list.containsAll(listRes));
    }
    @Test
    public void testFailGroupAndSemester()  {
        RegisterStudentDtoRequest dto = new RegisterStudentDtoRequest("Иван",
                "Иван","Иванович","kdfgkgg","jdjggggg",
                null,48);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<RegisterStudentDtoRequest>> constrains = validator.validate(dto);
        assertEquals(2, constrains.size());
        List<String> list = new ArrayList<>();
        List<String> listRes = new ArrayList<>();
        list.add("semester");
        list.add("group");
        for (ConstraintViolation<RegisterStudentDtoRequest> constrain : constrains) {

            listRes.add(constrain.getPropertyPath().toString());
        }
        assertTrue(list.containsAll(listRes));
    }
}
