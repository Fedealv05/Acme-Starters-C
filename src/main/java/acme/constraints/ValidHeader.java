
package acme.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Target({
	ElementType.FIELD, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation

@NotBlank
@Length(min = 1, max = 75)

public @interface ValidHeader {

	String message() default "{acme.validation.header.message}";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
