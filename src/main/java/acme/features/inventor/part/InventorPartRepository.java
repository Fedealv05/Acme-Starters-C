
package acme.features.inventor.part;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Part;

@Repository
public interface InventorPartRepository extends AbstractRepository {

	List<Part> findByInventionId(int inventionId);

	@Query("select i from Part i where i.id = :id")
	Part findPartById(int id);

}
