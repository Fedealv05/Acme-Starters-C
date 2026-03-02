
package acme.entities.campaigns;

import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface CampaignRepository extends AbstractRepository {

	Campaign findCampaignByTicker(String ticker);
}
