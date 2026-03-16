
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class InventorInventionDeleteService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		boolean createdByThePrincipal;
		createdByThePrincipal = this.invention.getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

		status = this.invention != null && createdByThePrincipal && this.invention.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void execute() {
		this.repository.delete(this.invention);
	}
	@Override
	public void validate() {
		super.validateObject(this.invention);
	}

	@Override
	public void bind() {
		super.bindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "cost", "monthsActive", "draftMode");
		super.unbindGlobal("inventorId", this.invention.getInventor().getId());
	}

}
