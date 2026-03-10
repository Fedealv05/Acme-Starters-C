
package acme.entities.sponsorships;

import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	Sponsorship findSponsorshipByTicker(String ticker);

}
