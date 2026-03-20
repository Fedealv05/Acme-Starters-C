
package acme.features.sponsor.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.realms.Sponsor;

@Service
public class SponsorDonationDeleteService extends AbstractService<Sponsor, Donation> {

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
			boolean isOwner = this.donation.getSponsorship().getSponsor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
			boolean isDraft = this.donation.getSponsorship().getDraftMode();

			status = isOwner && isDraft;
		}

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.donation, "name", "notes", "money", "kind");
	}

	@Override
	public void validate() {
		// No specific validation rules for deletion
	}

	@Override
	public void execute() {
		this.repository.delete(this.donation);
	}

	@Override
	public void unbind() {

		super.unbindObject(this.donation, "name", "notes", "money", "kind");

		super.unbindGlobal("draftMode", this.donation.getSponsorship().getDraftMode());
	}

}
