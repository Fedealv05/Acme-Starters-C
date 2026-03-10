
package acme.features.inventor.invention;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Controller
public class InventorInventionController extends AbstractController<Inventor, Invention> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", InventorInventionListService.class);
		super.addBasicCommand("show", InventorInventionShowService.class);

	}
}
