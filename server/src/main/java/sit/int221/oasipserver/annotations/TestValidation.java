package sit.int221.oasipserver.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = TestValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface TestValidation {
    String message() default "{com.tericcabrel.hotel.constraints.BirthDate.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
