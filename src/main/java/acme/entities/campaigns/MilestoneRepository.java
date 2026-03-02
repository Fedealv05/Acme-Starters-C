
package acme.entities.campaigns;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acme.client.repositories.AbstractRepository;

public interface MilestoneRepository extends AbstractRepository {

	@Query("SELECT sum(m.effort) FROM Milestone m WHERE m.campaign.id = :campaignId")
	Double findTotalEffortByCampaignId(@Param("campaignId") int campaignId);

	@Query("select count(p) from Milestone p where p.campaign.id = :id")
	long countByCampaignId(@Param("id") int id);
}
