
package acme.features.sponsor.donation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface SponsorDonationRepository extends AbstractRepository {

	@Query("select d from Donation d where d.sponsorship.id = :sponsorshipId")
	Collection<Donation> findBySponsorshipId(int sponsorshipId);

	@Query("select d from Donation d where d.id = :id")
	Donation findDonationById(int id);

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findSponsorshipById(int id);

}
