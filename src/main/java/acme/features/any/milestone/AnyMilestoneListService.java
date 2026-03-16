
package acme.features.any.milestone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Milestone;

@Service
public class AnyMilestoneListService extends AbstractService<Any, Milestone> {

	@Autowired
	private AnyMilestoneRepository	repository;

	private List<Milestone>			milestones;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("campaignId", int.class);
		this.milestones = this.repository.findByCampaignId(id);
	}

	@Override
	public void authorise() {
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "effort", "kind");
	}

}
