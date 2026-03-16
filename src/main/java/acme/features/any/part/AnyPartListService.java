
package acme.features.any.part;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.features.any.invention.AnyInventionRepository;

@Service
public class AnyPartListService extends AbstractService<Any, Part> {

	@Autowired
	private AnyPartRepository		repository;

	@Autowired
	private AnyInventionRepository	inventionRepository;

	private List<Part>				parts;

	private Invention				invention;


	@Override
	public void load() {

		int id = this.getRequest().getData("inventionId", int.class);

		this.invention = this.inventionRepository.findInventionById(id);

		this.parts = this.repository.findByInventionId(id);
	}

	@Override
	public void authorise() {
		boolean status;//comprobar que pasa si no hay partes
		status = this.invention != null && this.parts.stream().allMatch(i -> i.getInvention().getDraftMode() == false);
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "cost", "kind");
	}

}
