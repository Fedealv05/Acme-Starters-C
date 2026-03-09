
package acme.features.any.tactic;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.entities.strategy.Tactic;

@Controller
public class AnyTacticController extends AbstractController<Any, Tactic> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AnyTacticListService.class);
		super.addBasicCommand("show", AnyTacticShowService.class);

	}

}
