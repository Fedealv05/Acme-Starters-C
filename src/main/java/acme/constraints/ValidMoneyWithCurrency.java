
package acme.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({
	ElementType.FIELD, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Constraint(validatedBy = MoneyWithCurrencyValidator.class)

public @interface ValidMoneyWithCurrency {

	// Custom properties ------------------------------------------------------

	double min() default Double.NaN;
	double max() default Double.NaN;

	// Standard validation properties -----------------------------------------

	String message() default "{placeholder}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
