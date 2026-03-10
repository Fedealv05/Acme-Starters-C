
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface AnySponsorshipRepository extends AbstractRepository {

	Collection<Sponsorship> findByDraftModeFalse();

	Sponsorship findSponsorshipById(int id);

}
