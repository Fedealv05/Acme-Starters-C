
package acme.features.spokesperson.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignListService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private List<Campaign>					campaigns;


	@Override
	public void load() {
		int spokespersonId;

		spokespersonId = super.getRequest().getPrincipal().getActiveRealm().getId();

		this.campaigns = this.repository.findBySpokespersonId(spokespersonId);
	}

	@Override
	public void authorise() {
		boolean status = this.campaigns.stream().map(i -> i.getSpokesperson().getId()).allMatch(i -> i == super.getRequest().getPrincipal().getActiveRealm().getId());
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
