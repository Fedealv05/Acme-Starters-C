
package acme.entities;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface DonationRepository extends AbstractRepository {

	@Query("SELECT SUM(d.money.amount) FROM Donation d WHERE d.sponsorship.id = :sponsorshipId")
	Double totalMoneyBySponsorshipId(int sponsorshipId);

}
