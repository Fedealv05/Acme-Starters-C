
package acme.features.inventor.invention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class InventorInventionListService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;

	private List<Invention>				inventions;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getPrincipal().getActiveRealm().getId();

		this.inventions = this.repository.findByInventorId(id);
	}

	@Override
	public void authorise() {
		boolean status = this.inventions.stream().map(i -> i.getInventor().getId()).allMatch(i -> i == super.getRequest().getPrincipal().getActiveRealm().getId());
		;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.inventions, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
