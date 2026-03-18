
package acme.features.spokesperson.milestone;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Milestone;
import acme.realms.Spokesperson;

@Controller
public class SpokespersonMilestoneController extends AbstractController<Spokesperson, Milestone> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SpokespersonMilestoneListService.class);
		super.addBasicCommand("show", SpokespersonMilestoneShowService.class);
		super.addBasicCommand("create", SpokespersonMilestoneCreateService.class);
		super.addBasicCommand("update", SpokespersonMilestoneUpdateService.class);
		super.addBasicCommand("delete", SpokespersonMilestoneDeleteService.class);

	}

}
