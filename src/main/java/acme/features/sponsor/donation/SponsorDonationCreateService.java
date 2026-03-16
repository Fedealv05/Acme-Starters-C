
package acme.features.sponsor.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.features.sponsor.sponsorship.SponsorSponsorshipRepository;
import acme.realms.Sponsor;

@Service
public class SponsorDonationCreateService extends AbstractService<Sponsor, Donation> {

	@Autowired
	private SponsorDonationRepository		repository;

	@Autowired
	private SponsorSponsorshipRepository	sponsorshipRepository;

	private Donation						donation;

	private Sponsorship						sponsorship;


	@Override
	public void load() {
		int sponsorshipId;

		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.sponsorship = this.sponsorshipRepository.findSponsorshipById(sponsorshipId);

		this.donation = super.newObject(Donation.class);
		this.donation.setSponsorship(this.sponsorship);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean sponsorshipCreatedByPrincipal;

		sponsorshipCreatedByPrincipal = this.donation.getSponsorship().getSponsor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.donation.getSponsorship().getDraftMode() && sponsorshipCreatedByPrincipal;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {

		super.bindObject(this.donation, "name", "notes", "money", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.donation);
	}

	@Override
	public void execute() {
		this.repository.save(this.donation);
	}

	@Override
	public void unbind() {

		super.unbindObject(this.donation, "name", "notes", "money", "kind");

		super.unbindGlobal("draftMode", this.donation.getSponsorship().getDraftMode());
		super.unbindGlobal("sponsorshipId", this.sponsorship.getId());
	}
}
