
package acme.features.any.fundraiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.realms.Fundraiser;

@Service
public class AnyFundraiserShowService extends AbstractService<Any, Fundraiser> {

	@Autowired
	private AnyFundraiserRepository	repository;

	private Fundraiser				fundraiser;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.fundraiser = this.repository.findFundraiserById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		if (this.fundraiser != null)
			status = true;
		else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.fundraiser, "bank", "statement", "agent");
	}

}
