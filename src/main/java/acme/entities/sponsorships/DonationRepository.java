
package acme.entities.sponsorships;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface DonationRepository extends AbstractRepository {

	@Query("SELECT SUM(d.money) FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Double totalMoneyBySponsorshipId(int sponsorshipId);

}
