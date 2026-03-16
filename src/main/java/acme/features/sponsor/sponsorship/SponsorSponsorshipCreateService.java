
package acme.features.sponsor.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipCreateService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Sponsorship						sponsorship;


	@Override
	public void load() {
		Sponsor sponsor;
		sponsor = (Sponsor) super.getRequest().getPrincipal().getActiveRealm();
		this.sponsorship = super.newObject(Sponsorship.class);
		this.sponsorship.setDraftMode(true);
		this.sponsorship.setSponsor(sponsor);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.sponsorship);

		if (!super.getErrors().hasErrors("ticker")) {
			Sponsorship existing = this.repository.findSponsorshipByTicker(this.sponsorship.getTicker());
			super.state(existing == null, "ticker", "sponsorship.create.validation.uniqueTicker");
		}
	}

	@Override
	public void execute() {
		this.repository.save(this.sponsorship);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
		super.unbindGlobal("sponsorId", this.sponsorship.getSponsor().getId());
		super.unbindGlobal("draftMode", this.sponsorship.getDraftMode());
	}

}
