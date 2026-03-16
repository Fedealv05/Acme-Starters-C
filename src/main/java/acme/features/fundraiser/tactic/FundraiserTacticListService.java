
package acme.features.fundraiser.tactic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticListService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private int							strategyId;
	private Collection<Tactic>			tactics;


	@Override
	public void load() {
		this.strategyId = super.getRequest().getData("strategyId", int.class);

		this.tactics = this.repository.findManyByStrategyId(this.strategyId);
	}

	@Override
	public void authorise() {
		boolean status;

		Strategy strategy = this.repository.findStrategyById(this.strategyId);

		if (strategy != null)
			status = strategy.getFundraiser().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
	}

	@Override
	public void validate() {
	}

	@Override
	public void execute() {
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage", "kind");

		Strategy strategy = this.repository.findStrategyById(this.strategyId);

		super.unbindGlobal("strategyId", this.strategyId);
		super.unbindGlobal("draftMode", strategy.getDraftMode());
	}

}
