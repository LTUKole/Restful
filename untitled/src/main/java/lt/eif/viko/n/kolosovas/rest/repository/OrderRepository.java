package lt.eif.viko.n.kolosovas.rest.repository;

import lt.eif.viko.n.kolosovas.rest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;//CRUD (create,read,update,delete)

public interface OrderRepository extends JpaRepository<Order, Long> {
//Data base get access to make changes
}
