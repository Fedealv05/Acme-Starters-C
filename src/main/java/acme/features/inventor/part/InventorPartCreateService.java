
package acme.features.inventor.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.features.inventor.invention.InventorInventionRepository;
import acme.realms.Inventor;

@Service
public class InventorPartCreateService extends AbstractService<Inventor, Part> {

	@Autowired
	private InventorPartRepository		repository;

	@Autowired
	private InventorInventionRepository	inventionRepository;

	private Part						part;

	private Invention					invention;


	@Override
	public void load() {
		int id = this.getRequest().getData("inventionId", int.class);
		this.invention = this.inventionRepository.findInventionById(id);

		this.part = super.newObject(Part.class);
		this.part.setInvention(this.invention);

	}

	@Override
	public void authorise() {
		boolean status;
		String method;
		boolean inventionCreatedByPrincipal;

		method = super.getRequest().getMethod();

		if (method.equals("GET"))
			status = true;
		else {

			inventionCreatedByPrincipal = this.part.getInvention().getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
			status = this.part.getInvention().getDraftMode() && inventionCreatedByPrincipal;
		}
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.part, "name", "description", "cost", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.part);
	}

	@Override
	public void execute() {
		this.repository.save(this.part);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
		super.unbindGlobal("draftMode", this.part.getInvention().getDraftMode());
		super.unbindGlobal("inventionId", this.invention.getId());

	}
}
