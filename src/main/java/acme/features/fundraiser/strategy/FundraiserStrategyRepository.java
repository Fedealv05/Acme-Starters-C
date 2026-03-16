
package acme.features.fundraiser.strategy;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Repository
public interface FundraiserStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.fundraiser.id = :fundraiserId")
	List<Strategy> findManyByFundraiserId(@Param("fundraiserId") int fundraiserId);

	@Query("select s from Strategy s left join fetch s.tactics where s.id = :id")
	Strategy findOneById(@Param("id") int id);

	@Query("select s from Strategy s where s.ticker = :ticker")
	Strategy findOneByTicker(@Param("ticker") String ticker);

	@Query("select t from Tactic t where t.strategy.id = :id")
	Collection<Tactic> findTacticsByStrategyId(@Param("id") int id);

	@Query("select f from Fundraiser f where f.id = :id")
	Fundraiser findFundraiserById(@Param("id") int id);
}
