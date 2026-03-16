
package acme.features.sponsor.sponsorship;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Controller
public class SponsorSponsorshipController extends AbstractController<Sponsor, Sponsorship> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SponsorSponsorshipListService.class);
		super.addBasicCommand("show", SponsorSponsorshipShowService.class);
		super.addBasicCommand("create", SponsorSponsorshipCreateService.class);
		super.addBasicCommand("update", SponsorSponsorshipUpdateService.class);
		super.addBasicCommand("delete", SponsorSponsorshipDeleteService.class);

		super.addCustomCommand("publish", "update", SponsorSponsorshipPublishService.class);

	}

}
