package lt.eif.viko.n.kolosovas.rest.main;

import lt.eif.viko.n.kolosovas.rest.repository.OrderRepository;
import lt.eif.viko.n.kolosovas.rest.collect.OrderCollect;
import lt.eif.viko.n.kolosovas.rest.model.Order;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.eif.viko.n.kolosovas.rest.errors.OrderNotFound;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderAssembler {

    private final OrderRepository repository;

    private final OrderCollect assembler;

    OrderAssembler(OrderRepository repository, OrderCollect assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all(){

        List<EntityModel<Order>> orders = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderAssembler.class).all()).withSelfRel());
    }

    @PutMapping("/orders/{id}")
    ResponseEntity<?> updateOrder(@RequestBody Order newOrder, @PathVariable Long id){

        Order updatedOrder = repository.findById(id)
                .map(order -> {
                    order.setOrderDate(newOrder.getOrderDate());
                    order.setBuyers(newOrder.getBuyers());
                    order.setGames(newOrder.getGames());
                    return repository.save(order);
                })
                .orElseGet(()->{
                    newOrder.setId(id);
                    return repository.save(newOrder);
                });

        EntityModel<Order> entityModel = assembler.toModel(updatedOrder);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/orders/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("orders/{id}")
    public EntityModel<Order> one(@PathVariable Long id){

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));

        return assembler.toModel(order);
    }

    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody Order newOrder){

        EntityModel<Order> entityModel = assembler.toModel(repository.save(newOrder));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
