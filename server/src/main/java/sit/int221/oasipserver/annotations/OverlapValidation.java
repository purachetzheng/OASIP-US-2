package sit.int221.oasipserver.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OverlapValidator.class)
public @interface OverlapValidation {
    //error message
    public String message() default "Invalid color: must be RED, GREEN or BLUE";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};

    public boolean hasCategoryId() default false;
}
//https://reflectoring.io/bean-validation-with-spring-boot/