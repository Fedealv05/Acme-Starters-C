
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.TacticKind;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticCreateService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Tactic						tactic;


	@Override
	public void load() {
		int strategyId;
		Strategy strategy;

		strategyId = super.getRequest().getData("strategyId", int.class);

		strategy = this.repository.findStrategyById(strategyId);

		this.tactic = super.newObject(Tactic.class);
		this.tactic.setStrategy(strategy);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean createdByThePrincipal;

		createdByThePrincipal = this.tactic.getStrategy().getFundraiser().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

		status = createdByThePrincipal && this.tactic.getStrategy().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.tactic);
	}

	@Override
	public void execute() {
		this.repository.save(this.tactic);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
		super.unbindGlobal("strategyId", this.tactic.getStrategy().getId());
		SelectChoices choices = SelectChoices.from(TacticKind.class, this.tactic.getKind());
		super.unbindGlobal("tacticKinds", choices);
	}

}
