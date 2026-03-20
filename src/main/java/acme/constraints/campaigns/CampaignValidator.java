
package acme.constraints.campaigns;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.CampaignRepository;
import acme.entities.campaigns.MilestoneRepository;

@Validator
public class CampaignValidator extends AbstractValidator<ValidCampaign, Campaign> {

	@Autowired
	private CampaignRepository	repository;

	@Autowired
	private MilestoneRepository	milestoneRepository;


	@Override
	public boolean isValid(final Campaign campaign, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (campaign == null)
			result = true;
		else {
			{
				boolean uniqueCampaign;
				Campaign existingCampaign;

				existingCampaign = this.repository.findCampaignByTicker(campaign.getTicker());
				uniqueCampaign = existingCampaign == null || existingCampaign.equals(campaign);

				super.state(context, uniqueCampaign, "ticker", "acme.valid.campaign.duplicated-ticker.message");

			}
			{
				boolean validTimeInterval;
				validTimeInterval = campaign.getStartMoment() == null || campaign.getEndMoment() == null || campaign.getStartMoment().before(campaign.getEndMoment());

				super.state(context, validTimeInterval, "*", "acme.validation.campaign.timeInterval.message");
			}

			{
				boolean validMilestones;
				if (campaign.getDraftMode())
					validMilestones = true;
				else {
					long count = this.milestoneRepository.countByCampaignId(campaign.getId());
					validMilestones = count > 0;
				}

				super.state(context, validMilestones, "draftMode", "acme.validation.campaign.milestones.message");
			}
			return !super.hasErrors(context);

		}
		return result;
	}
}
