
package acme.features.any.spokesperson;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.realms.Spokesperson;

@Controller
public class AnySpokespersonController extends AbstractController<Any, Spokesperson> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("show", AnySpokespersonShowService.class);
	}

}
