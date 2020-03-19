package Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import Domain.Book;
import java.util.ArrayList;
@XmlRootElement(name = "Books")
public class xmlBookRepo {
    ArrayList<Book> bookList = new ArrayList<>();

    xmlBookRepo(){}

    @XmlElement(name = "Book")
    ArrayList<Book> getBookList(){
        return this.bookList;
    }

   public void setBookList(ArrayList<Book> b){
        this.bookList = b;
    }


}
