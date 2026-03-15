
package acme.features.any.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;

@Service
public class AnyDonationShowService extends AbstractService<Any, Donation> {

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
	public void authorise() { //PREGUNTAR
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.donation, "name", "notes", "money", "kind");
	}

}
