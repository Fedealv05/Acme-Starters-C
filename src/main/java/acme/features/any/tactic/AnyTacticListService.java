
package acme.features.any.tactic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;

@Service
public class AnyTacticListService extends AbstractService<Any, Tactic> {

	@Autowired
	private AnyTacticRepository	repository;

	private List<Tactic>		tactics;


	@Override
	public void load() {

		int id = this.getRequest().getData("strategyId", int.class);

		this.tactics = this.repository.findByStrategyId(id);
	}

	@Override
	public void authorise() {
		boolean status;

		int strategyId = super.getRequest().getData("strategyId", int.class);

		Strategy strategy = this.repository.findStrategyById(strategyId);

		if (strategy != null)
			status = !strategy.getDraftMode();
		else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage", "kind");
	}

}
