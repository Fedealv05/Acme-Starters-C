
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticShowService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Tactic						tactic;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findOneById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int fundraiserId;
		fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();
		status = this.tactic != null && this.tactic.getStrategy().getFundraiser().getId() == fundraiserId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
	}

}
