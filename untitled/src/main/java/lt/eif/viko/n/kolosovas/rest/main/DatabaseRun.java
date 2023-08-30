package lt.eif.viko.n.kolosovas.rest.main;

import lt.eif.viko.n.kolosovas.rest.repository.OrderRepository;
import lt.eif.viko.n.kolosovas.rest.model.Order;
import lt.eif.viko.n.kolosovas.rest.model.Game;
import lt.eif.viko.n.kolosovas.rest.model.Buyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
//annotation indicates that the class contains configuration settings for the Spring application.
public class DatabaseRun {

    private static final Logger log = LoggerFactory.getLogger(DatabaseRun.class);
    //This creates a static logger
    @Bean
    CommandLineRunner initDatabase(OrderRepository repository) {
        // Create the first game, buyer, and order
        Game game1 = new Game("Call of Fantasy", "Epic adventure in a fantasy world", "Action RPG", "Single-player", 49);
        Buyer buyer1 = new Buyer ("Jonas", "Jhonson");
        Order order1 = new Order("2023-7-29", List.of(buyer1), List.of(game1));

        // Create the second game, gamers, and order
        Game game2 = new Game("Galactic Conquest", "Explore the universe and conquer planets", "Space Strategy", "Multiplayer", 30);
        Buyer buyer2 = new Buyer("Alex", "Smith");
        Buyer buyer3 = new Buyer("Emily", "Johnson");
        Order order2 = new Order("2024-04-10", List.of(buyer2, buyer3), List.of(game2));

        // Define a CommandLineRunner bean to initialize the database with the created orders
        // This method will be executed when the application starts
        return args -> {
            log.info("Loading " + repository.save(order1));
            log.info("Loading " + repository.save(order2));
        };
    }
}
