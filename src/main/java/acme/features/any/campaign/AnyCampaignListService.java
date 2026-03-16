
package acme.features.any.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;

@Service
public class AnyCampaignListService extends AbstractService<Any, Campaign> {

	@Autowired
	private AnyCampaignRepository	repository;

	private List<Campaign>			campaigns;


	@Override
	public void load() {

		this.campaigns = this.repository.findByDraftModeFalse();
	}

	@Override
	public void authorise() {
		boolean status = true;
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "name", "startMoment", "endMoment");
	}

}
