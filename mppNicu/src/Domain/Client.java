package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends Entity<Integer> {
    private String fullName;
    private ArrayList<Book> booksBought;
    private int moneySpet;

    /**
     *
     * @param clientId
     * @param fullName
     */

    public Client(int clientId, String fullName) {
        super(clientId);
        this.fullName = fullName;
        this.booksBought = new ArrayList<Book>();
        this.moneySpet = 0;

    }

    @Override
    public void  setEntity(Entity e){
        if (e instanceof Client){
            this.setId(((Client) e).getId());
            this.fullName=((Client) e).getFullName();
            this.booksBought=((Client) e).get_books_bought();
            this.moneySpet=((Client) e).get_money_spent();
        }

    }


    public ArrayList<Book> get_books_bought(){
        return this.booksBought;
    }
    public int get_money_spent(){
        return this.moneySpet;
    }
    public void add_book(Book b){
        this.booksBought.add(b);
    }
    public void add_money_spent(int price){
        this.moneySpet += price;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        Client c = (Client) o;
        return c.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Client id = " + this.getId() + " " +
                "fullName='" + fullName + '\'' +
                ", booksBought=" + booksBought +
                ", moneySpet=" + moneySpet +
                '}';
    }
}
