package lt.eif.viko.n.kolosovas.rest.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Order extends Game {

    private @Id @GeneratedValue Long id;
    private String orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Buyer> buyers; // Changed attribute name to "buyers"

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Game> games; // Changed attribute name to "games"

    public Order() {}

    public Order(Long id, String orderDate, List<Buyer> buyers, List<Game> games) {
        this.id = id;
        this.orderDate = orderDate;
        this.buyers = buyers;
        this.games = games;
    }

    public Order(String orderDate, List<Buyer> buyers, List<Game> games) {
        this.orderDate = orderDate;
        this.buyers = buyers;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;
        return Objects.equals(this.id, order.id)
                && Objects.equals(this.orderDate, order.orderDate)
                && this.games.equals(order.games)
                && this.buyers.equals(order.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.orderDate, this.games, this.buyers);
    }

    @Override
    public String toString() {
        return String.format("Order:\n\t Date = %s\n\t" +
                        "Buyers: \n\t%s" +
                        "Games:\n\t\t%s",
                this.orderDate,
                this.buyers,
                this.games,
                constructBuyerString(),
                constructGameString());
    }

    private String constructBuyerString() {
        String resultBuyer = "";
        for (Buyer buyer : this.buyers) {
            resultBuyer += buyer.toString();
        }
        return resultBuyer;
    }

    private String constructGameString() {
        String resultGame = "";
        for (Game game : this.games) {
            resultGame += game.toString();
        }
        return resultGame;
    }
}
