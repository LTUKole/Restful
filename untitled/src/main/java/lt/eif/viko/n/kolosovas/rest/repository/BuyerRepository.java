package lt.eif.viko.n.kolosovas.rest.repository;

import lt.eif.viko.n.kolosovas.rest.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;//CRUD (create,read,update,delete)

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
//Data base get access to make changes
}
