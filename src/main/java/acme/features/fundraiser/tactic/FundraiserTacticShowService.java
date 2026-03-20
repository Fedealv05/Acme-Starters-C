
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.TacticKind;
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

		if (this.tactic != null && this.tactic.getStrategy() != null)
			status = this.tactic.getStrategy().getFundraiser().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
		super.unbindGlobal("draftMode", this.tactic.getStrategy().getDraftMode());
		SelectChoices choices = SelectChoices.from(TacticKind.class, this.tactic.getKind());
		super.unbindGlobal("tacticKinds", choices);
	}

}
