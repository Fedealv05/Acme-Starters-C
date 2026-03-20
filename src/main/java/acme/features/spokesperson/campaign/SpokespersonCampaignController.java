
package acme.features.spokesperson.campaign;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.campaigns.Campaign;
import acme.realms.Spokesperson;

@Controller
public class SpokespersonCampaignController extends AbstractController<Spokesperson, Campaign> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SpokespersonCampaignListService.class);
		super.addBasicCommand("show", SpokespersonCampaignShowService.class);
		super.addBasicCommand("create", SpokespersonCampaignCreateService.class);
		super.addBasicCommand("update", SpokespersonCampaignUpdateService.class);
		super.addBasicCommand("delete", SpokespersonCampaignDeleteService.class);

		super.addCustomCommand("publish", "update", SpokespersonCampaignPublishService.class);

	}

}
