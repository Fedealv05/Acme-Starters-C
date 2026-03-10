
package acme.features.any.donation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Donation;

@Repository
public interface AnyDonationRepository extends AbstractRepository {

	@Query("select d from Donation d where d.sponsorship.id = :sponsorshipId")
	Collection<Donation> findBySponsorshipId(int sponsorshipId);

	Donation findById(int id);

}
