package lt.eif.viko.n.kolosovas.rest.collect;

import lt.eif.viko.n.kolosovas.rest.main.OrderAssembler;
import lt.eif.viko.n.kolosovas.rest.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderCollect implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order){
        return EntityModel.of(order,
                WebMvcLinkBuilder.linkTo(methodOn(OrderAssembler.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderAssembler.class).all()).withRel("orders"));
    }
}
