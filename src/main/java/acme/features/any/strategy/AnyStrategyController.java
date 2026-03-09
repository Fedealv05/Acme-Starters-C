
package acme.features.any.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.entities.strategy.Strategy;

@Controller
public class AnyStrategyController extends AbstractController<Any, Strategy> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AnyStrategyListService.class);
		super.addBasicCommand("show", AnyStrategyShowService.class);

	}

}
