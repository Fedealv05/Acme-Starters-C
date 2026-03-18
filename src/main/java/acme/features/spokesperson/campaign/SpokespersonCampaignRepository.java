
package acme.features.spokesperson.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;

@Repository
public interface SpokespersonCampaignRepository extends AbstractRepository {

	List<Campaign> findBySpokespersonId(int spokepersonId);

	@Query("select c from Campaign c where c.id = :id")
	Campaign findCampaignById(int id);

	Campaign findByTicker(String ticker);
}
