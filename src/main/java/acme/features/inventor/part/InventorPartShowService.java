
package acme.features.inventor.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.PartKind;
import acme.entities.inventions.Part;
import acme.realms.Inventor;

@Service
public class InventorPartShowService extends AbstractService<Inventor, Part> {

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
		status = this.part != null && this.part.getInvention().getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.part, "name", "description", "cost", "kind");
		super.unbindGlobal("draftMode", this.part.getInvention().getDraftMode());
		super.unbindGlobal("inventionId", this.part.getInvention().getId());
		SelectChoices kinds = SelectChoices.from(PartKind.class, this.part.getKind());
		super.unbindGlobal("kinds", kinds);

	}

}
