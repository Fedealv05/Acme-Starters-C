
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipListService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Collection<Sponsorship>			sponsorships;


	@Override
	public void load() {
		int sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.sponsorships = this.repository.findBySponsorId(sponsorId);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.sponsorships, "ticker", "name", "startMoment", "endMoment", "draftMode");
	}

}
