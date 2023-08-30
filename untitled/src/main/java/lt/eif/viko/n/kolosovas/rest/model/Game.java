package lt.eif.viko.n.kolosovas.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private String creatorsName;
    private String name;
    private String seller;
    private String genre;
    private Integer cost;

    public Game() {
    }

    public Game(Long id, String creatorsName, String name, String seller, String genre, Integer cost) {
        this.id = id;
        this.creatorsName = creatorsName;
        this.name = name;
        this.seller = seller;
        this.genre = genre;
        this.cost = cost;
    }

    public Game(String creatorsName, String name, String seller, String genre, Integer cost) {
        this.creatorsName = creatorsName;
        this.name = name;
        this.seller = seller;
        this.genre = genre;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatorsName() {
        return creatorsName;
    }

    public void setCreatorsName(String creatorsName) {
        this.creatorsName = creatorsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(creatorsName, game.creatorsName) && Objects.equals(name, game.name) && Objects.equals(seller, game.seller) && Objects.equals(genre, game.genre) && Objects.equals(cost, game.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creatorsName, name, seller, genre, cost);
    }

    @Override
    public String toString() {
        return String.format("\tGame:\n\t\t\t\t" + "Creators Name = %s\n\t\t\t\t" + "Name = %s\n\t\t\t\t" + "Seller = %s\n\t\t\t\t" + "Genre = %s\n\t\t\t\t" + "Cost = %s\n\t\t\t\t", creatorsName, name, seller, genre, cost);
    }
}
