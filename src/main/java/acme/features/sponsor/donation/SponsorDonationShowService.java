
package acme.features.sponsor.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.DonationKind;
import acme.entities.sponsorships.Donation;
import acme.realms.Sponsor;

@Service
public class SponsorDonationShowService extends AbstractService<Sponsor, Donation> {

	@Autowired
	private SponsorDonationRepository	repository;

	private Donation					donation;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.donation = this.repository.findDonationById(id);

	}

	@Override
	public void authorise() {
		boolean status = false;

		if (this.donation != null) {
			int sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();

			boolean isOwner = this.donation.getSponsorship().getSponsor().getId() == sponsorId;
			boolean isPublished = !this.donation.getSponsorship().getDraftMode();

			status = isOwner || isPublished;
		}

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.donation, "name", "notes", "money", "kind");
		super.unbindGlobal("draftMode", this.donation.getSponsorship().getDraftMode());
		super.unbindGlobal("sponsorshipId", this.donation.getSponsorship().getId());
		SelectChoices choices = SelectChoices.from(DonationKind.class, this.donation.getKind());
		super.unbindGlobal("donationKinds", choices);
	}

}
