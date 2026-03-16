
package acme.features.sponsor.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorDonationListService extends AbstractService<Sponsor, Donation> {

	@Autowired
	private SponsorDonationRepository	repository;

	private Collection<Donation>		donations;

	private int							sponsorshipId;

	private Sponsorship					sponsorship;


	@Override
	public void load() {

		this.sponsorshipId = this.getRequest().getData("sponsorshipId", int.class);

		this.donations = this.repository.findBySponsorshipId(this.sponsorshipId);

		this.sponsorship = this.repository.findSponsorshipById(this.sponsorshipId);
	}

	@Override
	public void authorise() {
		boolean status;
		int sponsorId;
		Sponsorship sponsorship;

		sponsorship = this.repository.findSponsorshipById(this.sponsorshipId);
		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();

		status = sponsorship != null && sponsorship.getSponsor().getId() == sponsorId;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");
		super.unbindGlobal("draftMode", this.sponsorship.getDraftMode());
		super.unbindGlobal("sponsorshipId", this.sponsorship.getId());
	}

}
