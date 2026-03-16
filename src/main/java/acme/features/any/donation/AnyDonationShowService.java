
package acme.features.any.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;

@Service
public class AnyDonationShowService extends AbstractService<Any, Donation> {

	@Autowired
	private AnyDonationRepository	repository;

	private Donation				donation;

	private Sponsorship				sponsorship;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.donation = this.repository.findById(id);
		if (this.donation != null)
			this.sponsorship = this.repository.findSponsorshipById(this.donation.getSponsorship().getId());

	}

	@Override
	public void authorise() {
		boolean status;
		status = this.sponsorship != null && !this.donation.getSponsorship().getDraftMode();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.donation, "name", "notes", "money", "kind");
	}

}
