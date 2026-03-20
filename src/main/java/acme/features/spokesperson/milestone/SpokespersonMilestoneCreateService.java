
package acme.features.spokesperson.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.MilestoneKind;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.features.spokesperson.campaign.SpokespersonCampaignRepository;
import acme.realms.Spokesperson;

@Service
public class SpokespersonMilestoneCreateService extends AbstractService<Spokesperson, Milestone> {

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	@Autowired
	private SpokespersonCampaignRepository	campaignRepository;

	private Milestone						milestone;

	private Campaign						campaign;


	@Override
	public void load() {
		int id = this.getRequest().getData("campaignId", int.class);
		this.campaign = this.campaignRepository.findCampaignById(id);

		this.milestone = super.newObject(Milestone.class);
		this.milestone.setCampaign(this.campaign);
	}

	@Override
	public void authorise() {
		boolean status;
		String method;
		boolean campaingCreatedByPrincipal;

		method = super.getRequest().getMethod();

		if (this.campaign != null) {
			campaingCreatedByPrincipal = this.campaign.getSpokesperson().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = this.campaign.getDraftMode() && campaingCreatedByPrincipal;
		} else
			status = false;
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.milestone, "title", "achievements", "effort", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.milestone);
		if (this.milestone.getKind() != null) {
			MilestoneKind kind;
			kind = this.milestone.getKind();
			boolean validKind = kind.equals(MilestoneKind.TEASER) || kind.equals(MilestoneKind.ENGAGING) || kind.equals(MilestoneKind.CONVERSION);
			super.state(validKind, "kind", "part.create.validation.validKind");
		}

	}

	@Override
	public void execute() {
		this.repository.save(this.milestone);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		super.unbindGlobal("draftMode", this.milestone.getCampaign().getDraftMode());
		super.unbindGlobal("campaignId", this.milestone.getCampaign().getId());
		SelectChoices kinds = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());
		super.unbindGlobal("kinds", kinds);
	}
}
