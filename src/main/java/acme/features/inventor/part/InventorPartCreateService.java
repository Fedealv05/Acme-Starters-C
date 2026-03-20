
package acme.features.inventor.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.PartKind;
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

		if (this.invention != null) {

			inventionCreatedByPrincipal = this.invention.getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
			status = this.part.getInvention().getDraftMode() && inventionCreatedByPrincipal;

		} else
			status = false;
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.part, "name", "description", "cost", "kind");
	}

	@Override
	public void validate() {

		super.validateObject(this.part);
		if (this.part.getKind() != null) {
			PartKind kind;
			kind = this.part.getKind();
			boolean validKind = kind.equals(PartKind.CORE) || kind.equals(PartKind.MANDATORY) || kind.equals(PartKind.OPTIONAL);

			super.state(validKind, "kind", "part.create.validation.validKind");
		}
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
		SelectChoices kinds = SelectChoices.from(PartKind.class, this.part.getKind());
		super.unbindGlobal("kinds", kinds);

	}
}
