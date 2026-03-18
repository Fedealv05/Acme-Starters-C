
package acme.constraints.strategy;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;
import acme.entities.strategy.TacticRepository;

@Validator
public class StrategyValidator extends AbstractValidator<ValidStrategy, Strategy> {

	@Autowired
	private StrategyRepository	repository;

	@Autowired
	private TacticRepository	tacticRepository;


	@Override
	public boolean isValid(final Strategy strategy, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (strategy == null)
			result = true;
		else {
			{
				boolean uniqueTicker;
				Strategy existingStrategy;

				existingStrategy = this.repository.findStrategyByTicker(strategy.getTicker());
				uniqueTicker = existingStrategy == null || existingStrategy.equals(strategy);

				super.state(context, uniqueTicker, "ticker", "acme.validation.strategy.duplicated-ticker.message");
			}

			{
				boolean validTimeInterval = true;
				if (strategy.getStartMoment() != null && strategy.getEndMoment() != null)
					validTimeInterval = strategy.getStartMoment().before(strategy.getEndMoment());

				super.state(context, validTimeInterval, "*", "acme.validation.strategy.timeInterval.message");
			}
			boolean validTactics;
			if (strategy.getDraftMode())
				validTactics = true;

			else {
				long count = this.tacticRepository.countByStrategyId(strategy.getId());
				validTactics = count > 0;

				super.state(context, validTactics, "draftMode", "acme.validation.strategy.tactics.message");
			}

			result = !super.hasErrors(context);
		}
		return result;
	}
}
