
package acme.features.spokesperson.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.features.spokesperson.campaign.SpokespersonCampaignRepository;
import acme.realms.Spokesperson;

@Service
public class SpokespersonMilestoneListService extends AbstractService<Spokesperson, Milestone> {

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	@Autowired
	private SpokespersonCampaignRepository	campaignRepository;

	private List<Milestone>					milestones;

	private Campaign						campaign;


	@Override
	public void load() {

		int id = this.getRequest().getData("campaignId", int.class);

		this.campaign = this.campaignRepository.findCampaignById(id);
		this.milestones = this.repository.findByCampaignId(id);
	}

	@Override
	public void authorise() {
		boolean status = this.campaign != null && this.campaign.getSpokesperson().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
		super.unbindGlobal("draftMode", this.campaign.getDraftMode());
		super.unbindGlobal("campaignId", this.campaign.getId());
	}

}
