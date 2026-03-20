
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import acme.client.components.datatypes.Money;
import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.PropertyHelper;
import acme.internals.helpers.HibernateHelper;

@Validator
public class MoneyWithCurrencyValidator extends AbstractValidator<ValidMoneyWithCurrency, Money> {

	// Internal state ---------------------------------------------------------

	private double	lowerLimit;
	private double	upperLimit;

	// Initialiser ------------------------------------------------------------


	@Override
	public void initialise(final ValidMoneyWithCurrency annotation) {
		assert annotation != null;

		this.lowerLimit = annotation.min();
		if (Double.isNaN(this.lowerLimit))
			this.lowerLimit = PropertyHelper.getRequiredProperty("acme.data.money.minimum", double.class);

		this.upperLimit = annotation.max();
		if (Double.isNaN(this.upperLimit))
			this.upperLimit = PropertyHelper.getRequiredProperty("acme.data.money.maximum", double.class);
	}

	// AbstractValidator interface --------------------------------------------

	@Override
	public boolean isValid(final Money value, final ConstraintValidatorContext context) {
		// HINT: value can be null
		assert context != null;

		boolean result;
		HibernateConstraintValidatorContext hibernateContext;

		hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);

		if (value == null)
			result = true;
		else {
			result = value.getAmount() == null || this.lowerLimit <= value.getAmount() && value.getAmount() <= this.upperLimit;

			if (!result)
				HibernateHelper.replaceParameter(hibernateContext, "placeholder", "acme.validation.range.message", this.lowerLimit, this.upperLimit);
		}
		{
			boolean isEur;
			if (value == null)
				result = true;
			else {
				isEur = value.getCurrency().equals("EUR");
				if (!isEur) {
					result = false;
					super.state(context, isEur, "*", "acme.validation.moneyWithCurrency.isEur.message");
				}
			}
		}

		return result;
	}

}
