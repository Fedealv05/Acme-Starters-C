
package acme.features.any.strategy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;

@Repository
public interface AnyStrategyRepository extends AbstractRepository {

	List<Strategy> findByDraftModeFalse();

	@Query("select s from Strategy s left join fetch s.tactics where s.id = :id")
	Strategy findStrategyById(@Param("id") int id);
}
