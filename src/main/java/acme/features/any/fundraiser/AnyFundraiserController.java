
package acme.features.any.fundraiser;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.realms.Fundraiser;

@Controller
public class AnyFundraiserController extends AbstractController<Any, Fundraiser> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("show", AnyFundraiserShowService.class);

	}

}
