
package acme.features.sponsor.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.features.any.donation.AnyDonationRepository;
import acme.realms.Sponsor;

@Service
public class SponsorDonationShowService extends AbstractService<Sponsor, Donation> {

	@Autowired
	private AnyDonationRepository	repository;

	private Donation				donation;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.donation = this.repository.findById(id);

	}

	@Override
	public void authorise() {
		boolean status;
		int sponsorId;
		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		status = this.donation != null && this.donation.getSponsorship().getSponsor().getId() == sponsorId;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.donation, "name", "notes", "money", "kind");
	}

}
