
package acme.features.any.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;

@Service
public class AnyDonationListService extends AbstractService<Any, Donation> {

	@Autowired
	private AnyDonationRepository	repository;

	private Collection<Donation>	donations;

	private Sponsorship				sponsorship;


	@Override
	public void load() {

		int id = this.getRequest().getData("sponsorshipId", int.class);

		this.donations = this.repository.findBySponsorshipId(id);

		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {

		boolean status = this.sponsorship != null && this.donations.stream().allMatch(i -> i.getSponsorship().getDraftMode() == false);

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");
	}
}
