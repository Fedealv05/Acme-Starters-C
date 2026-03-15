
package acme.features.any.sponsor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.realms.Sponsor;

@Service
public class AnySponsorShowService extends AbstractService<Any, Sponsor> {

	@Autowired
	private AnySponsorRepository	repository;

	private Sponsor					sponsor;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.sponsor = this.repository.findSponsorById(id);
	}

	@Override
	public void authorise() { //PREGUNTAR
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsor, "address", "im", "gold");
	}

}
