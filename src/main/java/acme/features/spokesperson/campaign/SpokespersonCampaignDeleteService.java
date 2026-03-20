
package acme.features.spokesperson.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.features.spokesperson.milestone.SpokespersonMilestoneRepository;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignDeleteService extends AbstractService<Spokesperson, Campaign> {

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
	public void execute() {
		List<Milestone> milestones;

		int id;
		id = super.getRequest().getData("id", int.class);

		milestones = this.milestoneRepository.findByCampaignId(id);
		milestones.stream().forEach(m -> this.milestoneRepository.delete(m));

		this.repository.delete(this.campaign);
	}
	@Override
	public void validate() {
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "effort", "monthsActive", "draftMode");
		super.unbindGlobal("spokespersonId", this.campaign.getSpokesperson().getId());
	}

}
