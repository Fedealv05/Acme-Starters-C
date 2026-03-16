
package acme.features.sponsor.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipShowService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Sponsorship						sponsorship;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		int sponsorId;

		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		status = this.sponsorship != null && this.sponsorship.getSponsor().getId() == sponsorId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "totalMoney", "monthsActive", "draftMode");
		super.unbindGlobal("sponsorId", this.sponsorship.getSponsor().getId());
	}
}
