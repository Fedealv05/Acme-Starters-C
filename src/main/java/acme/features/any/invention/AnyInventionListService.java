
package acme.features.any.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;

@Service
public class AnyInventionListService extends AbstractService<Any, Invention> {

	@Autowired
	private AnyInventionRepository	repository;

	private List<Invention>			inventions;


	@Override
	public void load() {

		this.inventions = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment");
	}

}
