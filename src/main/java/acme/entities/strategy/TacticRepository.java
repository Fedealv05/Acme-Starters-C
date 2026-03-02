
package acme.entities.strategy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface TacticRepository extends AbstractRepository {

	@Query("select sum(t.expectedPercentage) from Tactic t where t.strategy.id = :strategyId")
	Double sumExpectedPercentageByStrategyId(@Param("strategyId") int strategyId);

	@Query("SELECT COUNT(t) FROM Tactic t WHERE t.strategy.id = :strategyId")
	long countByStrategyId(@Param("strategyId") int strategyId);
}
