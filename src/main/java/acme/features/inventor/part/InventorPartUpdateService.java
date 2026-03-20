
package acme.features.inventor.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.PartKind;
import acme.entities.inventions.Part;
import acme.realms.Inventor;

@Service
public class InventorPartUpdateService extends AbstractService<Inventor, Part> {

	@Autowired
	private InventorPartRepository	repository;

	private Part					part;


	@Override
	public void load() {

		int id;
		id = super.getRequest().getData("id", int.class);
		this.part = this.repository.findPartById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		if (this.part != null) {

			boolean createdByThePrincipal;
			createdByThePrincipal = this.part.getInvention().getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.part.getInvention().getDraftMode();
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
		SelectChoices kinds = SelectChoices.from(PartKind.class, this.part.getKind());
		super.unbindGlobal("kinds", kinds);

	}

}
