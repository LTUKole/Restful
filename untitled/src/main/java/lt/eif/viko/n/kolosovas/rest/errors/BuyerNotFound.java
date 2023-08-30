package lt.eif.viko.n.kolosovas.rest.errors;

public class BuyerNotFound extends RuntimeException {

    public BuyerNotFound(Long id){
        super("Error buyer not found " + id);
    }

}
