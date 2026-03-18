
package acme.features.inventor.invention;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.features.inventor.part.InventorPartRepository;
import acme.realms.Inventor;

@Service
public class InventorInventionPublishService extends AbstractService<Inventor, Invention> {

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
	public void bind() {
		super.bindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.invention);

		boolean hasAtLeastOnePart;
		List<Part> parts = this.partRepository.findByInventionId(this.invention.getId());
		hasAtLeastOnePart = parts.size() >= 1;

		super.state(hasAtLeastOnePart, "*", "invention.publish.validation.hasAtLeastOnePart");

		boolean validStartMoment;
		Date publishMoment = MomentHelper.getCurrentMoment();
		validStartMoment = MomentHelper.isAfter(this.invention.getStartMoment(), publishMoment);

		super.state(validStartMoment, "startMoment", "invention.publish.validation.validStartMoment");

		boolean validEndMoment;
		validEndMoment = MomentHelper.isAfter(this.invention.getEndMoment(), publishMoment);

		super.state(validEndMoment, "endMoment", "invention.publish.validation.validEndMoment");

	}

	@Override
	public void execute() {
		this.invention.setDraftMode(false);
		this.repository.save(this.invention);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "cost", "monthsActive", "draftMode");
		super.unbindGlobal("inventorId", this.invention.getInventor().getId());
	}

}
