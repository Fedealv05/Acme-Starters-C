
package acme.features.fundraiser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyShowService extends AbstractService<Fundraiser, Strategy> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;

	// AbstractService interface ----------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findOneById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		if (this.strategy != null) {
			int fundraiserId = super.getRequest().getPrincipal().getActiveRealm().getId();
			status = this.strategy.getFundraiser().getId() == fundraiserId;
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "expectedPercentage", "draftMode");

		super.unbindGlobal("fundraiserId", this.strategy.getFundraiser().getId());
	}

}
