/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.components.principals.UserAccount;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.realms.Provider;

@Service
public class AuthenticatedProviderCreateService extends AbstractService<Authenticated, Provider> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedProviderRepository	repository;

	private Provider						provider;

	// AbstractService<Authenticated, Provider> ---------------------------


	@Override
	public void load() {
		int userAccountId;
		UserAccount userAccount;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		userAccount = this.repository.findUserAccountById(userAccountId);

		this.provider = new Provider();
		this.provider.setUserAccount(userAccount);
	}

	@Override
	public void authorise() {
		boolean status;

		status = !super.getRequest().getPrincipal().hasRealmOfType(Provider.class);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.provider, "company", "sector");
	}

	@Override
	public void validate() {
		super.validateObject(this.provider);
	}

	@Override
	public void execute() {
		this.repository.save(this.provider);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.provider, "company", "sector");
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}

}
