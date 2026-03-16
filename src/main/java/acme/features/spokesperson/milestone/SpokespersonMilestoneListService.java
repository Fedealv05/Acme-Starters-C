
package acme.features.spokesperson.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.realms.Spokesperson;

@Service
public class SpokespersonMilestoneListService extends AbstractService<Spokesperson, Milestone> {

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	private List<Milestone>					milestones;

	private int								campaignId;


	@Override
	public void load() {

		this.campaignId = super.getRequest().getData("campaignId", int.class);
		this.milestones = this.repository.findByCampaignId(this.campaignId);
	}

	@Override
	public void authorise() {
		boolean status;
		int spokespersonId;
		Campaign campaign;

		campaign = this.repository.findCampaignById(this.campaignId);
		spokespersonId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = campaign != null && campaign.getSpokesperson().getId() == spokespersonId;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
	}

}
