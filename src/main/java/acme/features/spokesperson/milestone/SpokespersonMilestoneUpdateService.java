
package acme.features.spokesperson.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.MilestoneKind;
import acme.entities.campaigns.Milestone;
import acme.realms.Spokesperson;

@Service
public class SpokespersonMilestoneUpdateService extends AbstractService<Spokesperson, Milestone> {

	@Autowired
	private SpokespersonMilestoneRepository	repository;

	private Milestone						milestone;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		if (this.milestone != null) {
			boolean createdByThePrincipal;
			createdByThePrincipal = this.milestone.getCampaign().getSpokesperson().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();

			status = createdByThePrincipal && this.milestone.getCampaign().getDraftMode();
		} else
			status = false;

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.milestone, "title", "achievements", "effort", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.milestone);
		if (this.milestone.getKind() != null) {
			MilestoneKind kind;
			kind = this.milestone.getKind();
			boolean validKind = kind.equals(MilestoneKind.TEASER) || kind.equals(MilestoneKind.ENGAGING) || kind.equals(MilestoneKind.CONVERSION);
			super.state(validKind, "kind", "part.create.validation.validKind");
		}
	}

	@Override
	public void execute() {
		this.repository.save(this.milestone);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.milestone, "title", "achievements", "effort", "kind");
		super.unbindGlobal("draftMode", this.milestone.getCampaign().getDraftMode());
		SelectChoices kinds = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());
		super.unbindGlobal("kinds", kinds);
	}

}
