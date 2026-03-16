/*
 * AuthenticatedInventorRepository.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.inventor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.realms.Inventor;

@Repository
public interface AuthenticatedInventorRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);

}
