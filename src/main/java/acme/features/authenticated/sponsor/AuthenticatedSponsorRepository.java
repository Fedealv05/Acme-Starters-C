
package acme.features.authenticated.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.components.principals.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.realms.Sponsor;

@Repository
public interface AuthenticatedSponsorRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findUserAccountById(int id);

	@Query("select i from Sponsor i where i.userAccount.id = :id")
	Sponsor findSponsorByUserAccountId(int id);
}
