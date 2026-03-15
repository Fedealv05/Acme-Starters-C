
package acme.features.inventor.invention;

import java.util.List;

import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Part;

@Repository
public interface InventorPartRepository extends AbstractRepository {

	List<Part> findByInventionId(int inventionId);

}
