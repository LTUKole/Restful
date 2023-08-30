package lt.eif.viko.n.kolosovas.rest.errors;

public class OrderNotFound extends RuntimeException{

    public OrderNotFound(Long id){
        super("Error order not found " + id);
    }
}
