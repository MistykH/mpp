package Domain;

public class Book extends Entity<Integer> {
    private String autorName;
    private String title;
    private int year;
    private int price;

    public Book( int bookId, String autorName, String title, int year, int price) {
        super(bookId);
        this.autorName = autorName;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public int get_price(){
        return this.price;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
