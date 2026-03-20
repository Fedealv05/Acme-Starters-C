
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipDeleteService extends AbstractService<Sponsor, Sponsorship> {

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
		boolean createdByThePrincipal;

		createdByThePrincipal = this.sponsorship.getSponsor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		status = createdByThePrincipal && this.sponsorship.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void execute() {
		Collection<Donation> donations;
		int id;
		id = super.getRequest().getData("id", int.class);
		donations = this.repository.findDonationsBySponsorshipId(id);
		donations.stream().forEach(d -> this.repository.delete(d));

		this.repository.delete(this.sponsorship);
	}

	@Override
	public void validate() {
		// No specific validation rules for deletion
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "totalMoney", "monthsActive");
		super.unbindGlobal("sponsorId", this.sponsorship.getSponsor().getId());
	}

}
