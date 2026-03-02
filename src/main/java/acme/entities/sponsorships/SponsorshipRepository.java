
package acme.entities.sponsorships;

import acme.client.repositories.AbstractRepository;

public interface SponsorshipRepository extends AbstractRepository {

	Sponsorship findSponsorshipByTicker(String ticker);

}
