
package acme.features.fundraiser.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Controller
public class FundraiserTacticController extends AbstractController<Fundraiser, Tactic> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", FundraiserTacticListService.class);
		super.addBasicCommand("show", FundraiserTacticShowService.class);
		super.addBasicCommand("create", FundraiserTacticCreateService.class);
		super.addBasicCommand("update", FundraiserTacticUpdateService.class);
		super.addBasicCommand("delete", FundraiserTacticDeleteService.class);

	}

}
