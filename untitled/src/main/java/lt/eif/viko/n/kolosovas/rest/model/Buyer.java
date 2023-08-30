package lt.eif.viko.n.kolosovas.rest.model;


import jakarta.persistence.Entity;//    Imports entity anotation,and should be mapped to a database table
import jakarta.persistence.GeneratedValue;//    Generate auto ID
import jakarta.persistence.Id; //   making ID field as primary key
import java.util.Objects;

@Entity
public class Buyer {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;


    public Buyer(){}

    public Buyer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Buyer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //  Getters and setters methods are provided for ...
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Buyer)){
            return false;
        }
        Buyer buyer = (Buyer) o;
        return Objects.equals(this.id, buyer.id)
                && Objects.equals(this.firstName, buyer.firstName)
                && Objects.equals(this.lastName, buyer.lastName);
        //Compares two Buyer objects for equality based on their ...
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName);
    }
    // Generates a hash code based on ...

    @Override
    public String toString(){
        return String.format("\tName = %s\n\t\t" + "Last Name = %s\n\t\t",
                this.firstName,
                this.lastName);
        //Creates a formatted string representation
    }


}
