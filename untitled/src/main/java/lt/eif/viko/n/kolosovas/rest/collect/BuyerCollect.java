package lt.eif.viko.n.kolosovas.rest.collect;

import lt.eif.viko.n.kolosovas.rest.main.BuyerChanges;
import lt.eif.viko.n.kolosovas.rest.model.Buyer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuyerCollect implements RepresentationModelAssembler<Buyer, EntityModel<Buyer>> {

    @Override
    public EntityModel<Buyer> toModel(Buyer buyer){
        return EntityModel.of(buyer,
                WebMvcLinkBuilder.linkTo(methodOn(BuyerChanges.class).one(buyer.getId())).withSelfRel(),
                linkTo(methodOn(BuyerChanges.class).all()).withRel("buyers"));
    }
}
