
package acme.features.fundraiser.tactic;

import java.util.List;

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

	private List<Tactic>				tactics;

	private int							strategyId;


	@Override
	public void load() {

		this.strategyId = super.getRequest().getData("strategyId", int.class);
		this.tactics = this.repository.findManyByStrategyId(this.strategyId);
	}

	@Override
	public void authorise() {
		boolean status;
		int fundraiserId;
		Strategy strategy;

		strategy = this.repository.findStrategyById(this.strategyId);
		fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = strategy != null && strategy.getFundraiser().getId() == fundraiserId;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage", "kind");
	}

}
