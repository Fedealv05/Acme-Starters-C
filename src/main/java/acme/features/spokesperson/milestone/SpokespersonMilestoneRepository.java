
package acme.features.spokesperson.milestone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.Milestone;

@Repository
public interface SpokespersonMilestoneRepository extends AbstractRepository {

	List<Milestone> findByCampaignId(int campaignId);

	@Query("select m from Milestone m where m.id = :id")
	Milestone findMilestoneById(int id);

	@Query("select c from Campaign c where c.id = :id")
	Campaign findCampaignById(@Param("id") int id);
}
