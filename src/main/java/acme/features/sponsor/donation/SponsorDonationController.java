
package acme.features.sponsor.donation;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorships.Donation;
import acme.realms.Sponsor;

@Controller
public class SponsorDonationController extends AbstractController<Sponsor, Donation> {

	@PostConstruct
	protected void initialise() {

		super.setMediaType(MediaType.TEXT_HTML);
		super.addBasicCommand("list", SponsorDonationListService.class);
		super.addBasicCommand("show", SponsorDonationShowService.class);
		super.addBasicCommand("create", SponsorDonationCreateService.class);
		super.addBasicCommand("update", SponsorDonationUpdateService.class);
		super.addBasicCommand("delete", SponsorDonationDeleteService.class);

	}

}
