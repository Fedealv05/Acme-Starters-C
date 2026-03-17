
package acme.features.authenticated.sponsor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.components.principals.UserAccount;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.realms.Sponsor;

@Service
public class AuthenticatedSponsorCreateService extends AbstractService<Authenticated, Sponsor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorRepository	repository;

	private Sponsor							sponsor;


	@Override
	public void load() {
		int userAccountId;
		UserAccount userAccount;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		userAccount = this.repository.findUserAccountById(userAccountId);

		this.sponsor = new Sponsor();
		this.sponsor.setUserAccount(userAccount);
	}

	@Override
	public void authorise() {
		boolean status;

		status = !super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class);
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
