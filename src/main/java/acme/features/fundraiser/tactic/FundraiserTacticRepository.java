
package acme.features.fundraiser.tactic;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;

@Repository
public interface FundraiserTacticRepository extends AbstractRepository {

	@Query("select t from Tactic t where t.strategy.id = :strategyId")
	List<Tactic> findManyByStrategyId(@Param("strategyId") int strategyId);

	@Query("select t from Tactic t where t.id = :id")
	Tactic findOneById(@Param("id") int id);

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(@Param("id") int id);

}
