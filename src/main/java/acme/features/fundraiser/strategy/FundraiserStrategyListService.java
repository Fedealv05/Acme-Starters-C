
package acme.features.fundraiser.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyListService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private List<Strategy>					strategies;


	@Override
	public void load() {
		int fundraiserId;

		fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();

		this.strategies = this.repository.findManyByFundraiserId(fundraiserId);
	}

	@Override
	public void authorise() {
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.strategies, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
