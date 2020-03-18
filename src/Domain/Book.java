package Domain;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Book")
@XmlAccessorType (XmlAccessType.FIELD)
public class Book extends Entity<Integer>{

    private String autorName;

    private String title;

    private int year;

    private int price;



    public String getAutorName() {
        return autorName;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Book(){
        super(1);
    }
    /**
     *
     * @param bookId
     * @param autorName
     * @param title
     * @param year
     * @param price
     */


    public Book( int bookId, String autorName, String title, int year, int price) {
        super(bookId);
        this.autorName = autorName;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    /**
     *
     * @return price of the book
     */
    @Override
    public void setEntity(Entity e){
        if(e instanceof Book){
            this.setId(((Book) e).getId());
            this.autorName=((Book) e).getAutorName();
            this.title=((Book) e).getTitle();
            this.year=((Book) e).getYear();
            this.price=((Book) e).getPrice();
        }

    }




    @Override
    public boolean equals(Object o) {
        Book b = (Book) o;
        return b.getId() == this.getId();
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book id = " + this.getId() + " " +
                "autorName='" + autorName + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
