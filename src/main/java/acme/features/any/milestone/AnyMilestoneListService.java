
package acme.features.any.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;
import acme.features.any.campaign.AnyCampaignRepository;

@Service
public class AnyMilestoneListService extends AbstractService<Any, Milestone> {

	@Autowired
	private AnyMilestoneRepository	repository;

	@Autowired
	private AnyCampaignRepository	campaignRepository;

	private List<Milestone>			milestones;

	private Campaign				campaign;


	@Override
	public void load() {
		int id = this.getRequest().getData("campaignId", int.class);

		this.campaign = this.campaignRepository.findCampaignById(id);

		this.milestones = this.repository.findByCampaignId(id);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.campaign != null && !this.campaign.getDraftMode() && this.milestones.stream().allMatch(i -> i.getCampaign().getDraftMode() == false);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
	}

}
