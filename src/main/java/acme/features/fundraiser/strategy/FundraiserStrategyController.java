
package acme.features.fundraiser.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Controller
public class FundraiserStrategyController extends AbstractController<Fundraiser, Strategy> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", FundraiserStrategyListService.class);
		super.addBasicCommand("show", FundraiserStrategyShowService.class);
		super.addBasicCommand("create", FundraiserStrategyCreateService.class);
		super.addBasicCommand("update", FundraiserStrategyUpdateService.class);
		super.addBasicCommand("delete", FundraiserStrategyDeleteService.class);

		super.addCustomCommand("publish", "update", FundraiserStrategyPublishService.class);

	}

}
