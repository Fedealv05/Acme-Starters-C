
package acme.features.authenticated.sponsor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.realms.Sponsor;

@Service
public class AuthenticatedSponsorUpdateService extends AbstractService<Authenticated, Sponsor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorRepository	repository;

	private Sponsor							sponsor;

	// AbstractService interface ----------------------------------------------ç


	@Override
	public void load() {
		int userAccountId;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.sponsor = this.repository.findSponsorByUserAccountId(userAccountId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsor, "address", "im", "gold");
	}

	@Override
	public void validate() {
		super.validateObject(this.sponsor);
	}

	@Override
	public void execute() {
		this.repository.save(this.sponsor);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsor, "address", "im", "gold");
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}

}
