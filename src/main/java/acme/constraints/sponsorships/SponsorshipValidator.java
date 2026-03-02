
package acme.constraints.sponsorships;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsorships.DonationRepository;
import acme.entities.sponsorships.Sponsorship;

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

				// 1. Al menos una donación usando el COUNT
				{
					Long count = 0L;
					if (sponsorship.getId() > 0)
						count = this.donationRepository.countBySponsorshipId(sponsorship.getId());

					boolean hasDonations = count > 0;
					super.state(context, hasDonations, "draftMode", "acme.validation.sponsorship.at-least-one-donation.message");
				}

				// 2. Intervalo de tiempo válido en el futuro respecto al momento de publicación
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
