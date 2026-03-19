
package acme.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Target({
	ElementType.FIELD, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation

@Pattern(regexp = "^[A-Z]{2}[0-9]{2}-\\w{5,10}$")

public @interface ValidTicker {

	String message() default "{acme.validation.ticker.message}";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
