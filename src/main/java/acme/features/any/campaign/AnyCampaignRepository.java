
package acme.features.any.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;

@Repository
public interface AnyCampaignRepository extends AbstractRepository {

	List<Campaign> findByDraftModeFalse();

	@Query("select i from Campaign i where i.id = :id")
	Campaign findCampaignById(int id);
}
