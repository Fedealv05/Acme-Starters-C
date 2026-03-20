
package acme.features.fundraiser.tactic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.TacticKind;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticUpdateService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Tactic						tactic;


	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findOneById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		if (this.tactic != null && this.tactic.getStrategy() != null) {
			boolean isOwner = this.tactic.getStrategy().getFundraiser().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
			status = isOwner && this.tactic.getStrategy().getDraftMode();
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.tactic, "name", "notes", "expectedPercentage", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.tactic);

		if (this.tactic.getExpectedPercentage() != null) {
			Collection<Tactic> existingTactics = this.repository.findManyByStrategyId(this.tactic.getStrategy().getId());

			double totalPercentage = 0.0;

			if (existingTactics != null)
				for (Tactic t : existingTactics)
					if (t.getId() != this.tactic.getId())
						totalPercentage += t.getExpectedPercentage();

			totalPercentage += this.tactic.getExpectedPercentage();

			boolean validPercentage = totalPercentage <= 100.0;

			super.state(validPercentage, "expectedPercentage", "acme.validation.tactic.percentage.message");
		}
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
