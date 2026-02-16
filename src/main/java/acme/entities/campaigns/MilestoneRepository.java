
package acme.entities.campaigns;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;

public interface MilestoneRepository extends AbstractRepository {

	@Query("SELECT sum(m.effort) FROM Milestone m WHERE m.campaign.id = :campaignId")
	Double findTotalEffortByCampaignId(int campaignId);
}
