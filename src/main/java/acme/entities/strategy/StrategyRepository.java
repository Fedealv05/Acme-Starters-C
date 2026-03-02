
package acme.entities.strategy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface StrategyRepository extends AbstractRepository {

	@Query("SELECT s FROM Strategy s WHERE s.ticker = :ticker")
	Strategy findStrategyByTicker(@Param("ticker") String ticker);

}
