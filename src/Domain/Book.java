package Domain;

public class Book extends Entity<Integer> {
    private String autorName;
    private String title;
    private int year;

    public Book( int bookId, String autorName, String title, int year) {
        super(bookId);
        this.autorName = autorName;
        this.title = title;
        this.year = year;
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
    public String toString() {
        return "Book{" +
                "bookId=" + this.getId() +
                ", autorName='" + autorName + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
