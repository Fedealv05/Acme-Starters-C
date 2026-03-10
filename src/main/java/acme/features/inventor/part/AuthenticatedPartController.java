
package acme.features.inventor.part;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Part;
import acme.realms.Inventor;

@Controller
public class AuthenticatedPartController extends AbstractController<Inventor, Part> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", AuthenticatedPartListService.class);
		super.addBasicCommand("show", AuthenticatedPartShowService.class);

	}

}
