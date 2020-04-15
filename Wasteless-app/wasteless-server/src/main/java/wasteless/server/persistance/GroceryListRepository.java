package wasteless.server.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wasteless.server.model.GroceryList;

@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {
}
