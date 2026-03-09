
package acme.features.any.part;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.inventions.Part;

@Service
public class AnyPartListService extends AbstractService<Any, Part> {

	@Autowired
	private AnyPartRepository	repository;

	private List<Part>			parts;


	@Override
	public void load() {

		int id = this.getRequest().getData("inventionId", int.class);

		this.parts = this.repository.findByInventionId(id);
	}

	@Override
	public void authorise() { //PREGUNTAR
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "cost", "kind");
	}

}
