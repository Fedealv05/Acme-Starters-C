
package acme.features.any.milestone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaigns.Milestone;

@Repository
public interface AnyMilestoneRepository extends AbstractRepository {

	@Query("select i from Milestone i where i.id = :id")
	Milestone findMilestoneById(int id);

	List<Milestone> findByCampaignId(int id);
}
