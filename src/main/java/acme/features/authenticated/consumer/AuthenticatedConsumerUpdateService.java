/*
 * AuthenticatedConsumerUpdateService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Authenticated;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.realms.Consumer;

@Service
public class AuthenticatedConsumerUpdateService extends AbstractService<Authenticated, Consumer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedConsumerRepository	repository;

	private Consumer						consumer;

	// AbstractService interface ----------------------------------------------ç


	@Override
	public void load() {
		int userAccountId;

		userAccountId = super.getRequest().getPrincipal().getAccountId();
		this.consumer = this.repository.findConsumerByUserAccountId(userAccountId);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Consumer.class);
		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.consumer, "company", "sector");
	}

	@Override
	public void validate() {
		super.validateObject(this.consumer);
	}

	@Override
	public void execute() {
		this.repository.save(this.consumer);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.consumer, "company", "sector");
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}

}
