
package acme.features.inventor.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.features.inventor.part.InventorPartRepository;
import acme.realms.Inventor;

@Service
public class InventorInventionDeleteService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;

	@Autowired
	private InventorPartRepository		partRepository;

	private Invention					invention;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status = true;

		if (this.invention != null) {

			boolean createdByThePrincipal;
			createdByThePrincipal = this.invention.getInventor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.invention.getDraftMode();
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void execute() {
		List<Part> parts;

		int id;
		id = super.getRequest().getData("id", int.class);

		parts = this.partRepository.findByInventionId(id);
		parts.stream().forEach(m -> this.partRepository.delete(m));

		this.repository.delete(this.invention);
	}
	@Override
	public void validate() {
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
