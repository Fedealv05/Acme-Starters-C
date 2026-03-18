
package acme.features.spokesperson.campaign;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.features.spokesperson.milestone.SpokespersonMilestoneRepository;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignPublishService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	@Autowired
	private SpokespersonMilestoneRepository	milestoneRepository;

	private Campaign						campaign;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		if (this.campaign != null) {
			boolean createdByThePrincipal;
			createdByThePrincipal = this.campaign.getSpokesperson().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.campaign.getDraftMode();
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.campaign);

		boolean hasAtLeastOneMilestone;
		List<Milestone> milestones = this.milestoneRepository.findByCampaignId(this.campaign.getId());
		hasAtLeastOneMilestone = milestones.size() >= 1;

		super.state(hasAtLeastOneMilestone, "*", "campaign.publish.validation.hasAtLeastOneMilestone");

		boolean validStartMoment;
		Date publishMoment = MomentHelper.getCurrentMoment();
		validStartMoment = MomentHelper.isAfter(this.campaign.getStartMoment(), publishMoment);

		super.state(validStartMoment, "startMoment", "campaign.publish.validation.validStartMoment");

		boolean validEndMoment;
		validEndMoment = MomentHelper.isAfter(this.campaign.getEndMoment(), publishMoment);

		super.state(validEndMoment, "endMoment", "campaign.publish.validation.validEndMoment");

	}

	@Override
	public void execute() {
		this.campaign.setDraftMode(false);
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "effort", "monthsActive", "draftMode");
		super.unbindGlobal("spokespersonId", this.campaign.getSpokesperson().getId());
	}

}
