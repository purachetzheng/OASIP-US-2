package sit.int221.oasipserver.annotations;

import sit.int221.oasipserver.entities.Event;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestValidator implements ConstraintValidator<TestValidation, Event> {
    @Override
    public boolean isValid(Event s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
