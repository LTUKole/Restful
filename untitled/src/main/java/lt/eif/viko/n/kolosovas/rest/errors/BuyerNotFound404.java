package lt.eif.viko.n.kolosovas.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BuyerNotFound404 {

    @ResponseBody
    @ExceptionHandler(BuyerNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String BuyerNotFoundHandler(BuyerNotFound ex){
        return ex.getMessage();
    }
}
