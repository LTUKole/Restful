package lt.eif.viko.n.kolosovas.rest.main;

import lt.eif.viko.n.kolosovas.rest.repository.BuyerRepository;
import lt.eif.viko.n.kolosovas.rest.collect.BuyerCollect;
import lt.eif.viko.n.kolosovas.rest.errors.BuyerNotFound;
import lt.eif.viko.n.kolosovas.rest.model.Buyer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//handles HTTP requests and produces responses in JSON format
public class BuyerChanges {

    private final BuyerRepository repository;
    private final BuyerCollect assembler;

    BuyerChanges(BuyerRepository repository, BuyerCollect assembler){

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/buyers")
    //Handles a GET request to retrieve a list of all buyers
    public CollectionModel<EntityModel<Buyer>> all(){

        List<EntityModel<Buyer>> buyers = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(buyers, linkTo(methodOn(BuyerChanges.class).all()).withSelfRel());
    }

    @GetMapping("/buyers/{id}")
    //Handles a GET request to retrieve a single buyer by its ID
    public EntityModel<Buyer> one(@PathVariable Long id){

        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new BuyerNotFound(id));

        return assembler.toModel(buyer);
    }

    @PutMapping("/buyers/{id}")
    //Handles a PUT request to update a buyer.
    ResponseEntity<?> updateBuyers(@RequestBody Buyer newBuyer, @PathVariable Long id){

        Buyer updatedBuyers = repository.findById(id)
                .map(buyer ->{
                    buyer.setFirstName(newBuyer.getFirstName());
                    buyer.setLastName(newBuyer.getLastName());
                    return repository.save(buyer);
                })
                .orElseGet(()->{
                    newBuyer.setId(id);
                    return repository.save(newBuyer);
                });

        EntityModel<Buyer> entityModel = assembler.toModel(updatedBuyers);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
