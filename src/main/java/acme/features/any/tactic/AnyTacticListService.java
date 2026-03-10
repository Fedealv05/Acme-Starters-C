
package acme.features.any.tactic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
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
		boolean status = true;

		if (this.tactics != null && !this.tactics.isEmpty())
			status = !this.tactics.get(0).getStrategy().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "expectedPercentage", "kind");
	}

}
