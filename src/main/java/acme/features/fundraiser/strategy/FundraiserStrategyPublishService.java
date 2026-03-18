
package acme.features.fundraiser.strategy;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyPublishService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;


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
			boolean createdByThePrincipal = this.strategy.getFundraiser().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.strategy.getDraftMode();

		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.strategy);

		Collection<Tactic> tactics = this.repository.findTacticsByStrategyId(this.strategy.getId());
		boolean hasTactics = tactics != null && !tactics.isEmpty();

		super.state(hasTactics, "*", "acme.validation.strategy.tactics.message");

		if (this.strategy.getStartMoment() != null) {
			boolean validStartMoment;
			Date publishMoment = MomentHelper.getCurrentMoment();
			validStartMoment = MomentHelper.isAfter(this.strategy.getStartMoment(), publishMoment);
			super.state(validStartMoment, "startMoment", "fundraiser.strategy.publish.validation.validStartMoment");
		}

		if (this.strategy.getEndMoment() != null) {
			boolean validEndMoment;
			Date publishMoment = MomentHelper.getCurrentMoment();
			validEndMoment = MomentHelper.isAfter(this.strategy.getEndMoment(), publishMoment);
			super.state(validEndMoment, "endMoment", "fundraiser.strategy.publish.validation.validEndMoment");
		}
	}

	@Override
	public void execute() {
		this.strategy.setDraftMode(false);
		this.repository.save(this.strategy);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}
