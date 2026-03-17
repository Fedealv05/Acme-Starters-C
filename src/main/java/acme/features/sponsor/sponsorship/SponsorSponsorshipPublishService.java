
package acme.features.sponsor.sponsorship;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipPublishService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Sponsorship						sponsorship;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		boolean status;
		boolean createdByThePrincipal;

		createdByThePrincipal = this.sponsorship.getSponsor().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		status = this.sponsorship != null && createdByThePrincipal && this.sponsorship.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.sponsorship);

		Collection<Donation> donations = this.repository.findDonationsBySponsorshipId(this.sponsorship.getId());
		super.state(!donations.isEmpty(), "*", "sponsorship.publish.validation.hasAtLeastOneDonation");

		if (!super.getErrors().hasErrors("ticker")) {
			Sponsorship existing = this.repository.findSponsorshipByTicker(this.sponsorship.getTicker());
			boolean uniqueTicker = existing == null || existing.getId() == this.sponsorship.getId();
			super.state(uniqueTicker, "ticker", "sponsorship.publish.validation.uniqueTicker");
		}

		if (!super.getErrors().hasErrors("startMoment")) {
			Date publishMoment = MomentHelper.getCurrentMoment();
			super.state(MomentHelper.isAfter(this.sponsorship.getStartMoment(), publishMoment), "startMoment", "sponsorship.publish.validation.validStartMoment");
		}

		if (!super.getErrors().hasErrors("startMoment") && !super.getErrors().hasErrors("endMoment")) {
			Date publishMoment = MomentHelper.getCurrentMoment();
			super.state(MomentHelper.isAfter(this.sponsorship.getEndMoment(), publishMoment), "endMoment", "sponsorship.publish.validation.validEndMoment");
			super.state(MomentHelper.isAfter(this.sponsorship.getEndMoment(), this.sponsorship.getStartMoment()), "endMoment", "sponsorship.publish.validation.validInterval");
		}
	}

	@Override
	public void execute() {
		this.sponsorship.setDraftMode(false);
		this.repository.save(this.sponsorship);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
		super.unbindGlobal("sponsorId", this.sponsorship.getSponsor().getId());
		super.unbindGlobal("draftMode", this.sponsorship.getDraftMode());
	}

}
