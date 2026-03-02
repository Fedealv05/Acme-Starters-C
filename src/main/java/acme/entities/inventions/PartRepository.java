
package acme.entities.inventions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface PartRepository extends AbstractRepository {

	@Query("SELECT SUM(p.cost.amount) FROM Part p WHERE p.invention.id = :inventionId")
	Double sumPriceByInventionId(@Param("inventionId") int inventionId);

	@Query("select count(p) from Part p where p.invention.id = :id")
	long countByInventionId(@Param("id") int id);

}
