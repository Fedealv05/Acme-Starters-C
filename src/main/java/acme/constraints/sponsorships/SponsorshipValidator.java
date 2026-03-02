
package acme.constraints.sponsorships;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsorships.DonationRepository;
import acme.entities.sponsorships.Sponsorship;

@Validator
public class SponsorshipValidator extends AbstractValidator<ValidSponsorship, Sponsorship> {

	@Autowired
	private DonationRepository donationRepository;


	@Override
	protected void initialise(final ValidSponsorship annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Sponsorship sponsorship, final ConstraintValidatorContext context) {
		assert context != null;
		boolean result;

		if (sponsorship == null)
			result = true;
		else {
			if (!sponsorship.getDraftMode()) {

				// 1. Al menos una donación (Adaptado para 1 solo batch)
				{
					boolean hasDonations;

					if (sponsorship.getId() > 0) {
						// Si ya está en base de datos (Ej: el usuario lo está editando para publicarlo)
						Long count = this.donationRepository.countBySponsorshipId(sponsorship.getId());
						hasDonations = count > 0;
					} else
						// TRUCO POPULATOR: Si id == 0 es una inserción nueva. 
						// El populator lo necesita para poder guardar el Sponsorship antes que las donaciones.
						hasDonations = true;

					super.state(context, hasDonations, "draftMode", "acme.validation.sponsorship.at-least-one-donation.message");
				}

				// 2. Intervalo de tiempo válido en el futuro
				{
					boolean validInterval = false;
					if (sponsorship.getStartMoment() != null && sponsorship.getEndMoment() != null) {
						boolean startInFuture = MomentHelper.isAfter(sponsorship.getStartMoment(), MomentHelper.getCurrentMoment());
						boolean endAfterStart = MomentHelper.isAfter(sponsorship.getEndMoment(), sponsorship.getStartMoment());

						validInterval = startInFuture && endAfterStart;
					}
					super.state(context, validInterval, "startMoment", "acme.validation.sponsorship.invalid-interval.message");
				}
			}

			result = !super.hasErrors(context);
		}

		return result;
	}
}
