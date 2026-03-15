
package acme.constraints.inventions;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionRepository;
import acme.entities.inventions.PartRepository;

@Validator
public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	@Autowired
	private InventionRepository	repository;

	@Autowired
	private PartRepository		partRepository;


	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (invention == null)
			result = true;
		else {
			{
				boolean uniqueInvention;
				Invention existingInvention;

				existingInvention = this.repository.findInventionByTicker(invention.getTicker());
				uniqueInvention = existingInvention == null || existingInvention.equals(invention);

				super.state(context, uniqueInvention, "ticker", "acme.valid.invention.duplicated-ticker.message");

			}
			{
				boolean validTimeInterval;
				validTimeInterval = invention.getStartMoment() == null || invention.getEndMoment() == null || invention.getStartMoment().before(invention.getEndMoment());

				super.state(context, validTimeInterval, "*", "acme.validation.invention.timeInterval.message");
			}

			{
				boolean validParts;
				if (invention.getDraftMode())
					validParts = true;
				else {
					long count = this.partRepository.countByInventionId(invention.getId());
					validParts = count > 0;
				}

				super.state(context, validParts, "draftMode", "acme.validation.invention.parts.message");
			}
			return !super.hasErrors(context);

		}
		return result;
	}

}
