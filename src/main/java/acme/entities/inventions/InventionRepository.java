
package acme.entities.inventions;

import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

	Invention findInventionByTicker(String ticker);

}
