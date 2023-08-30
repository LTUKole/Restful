package lt.eif.viko.n.kolosovas.rest.main;

import lt.eif.viko.n.kolosovas.rest.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class OrderModel implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order){
        return EntityModel.of(order,
                linkTo(methodOn(OrderAssembler.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderAssembler.class).all()).withRel("orders"));
    }
}
