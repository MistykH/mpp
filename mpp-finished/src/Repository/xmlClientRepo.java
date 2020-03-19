package Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import Domain.Book;
import Domain.Client;

import java.util.ArrayList;
@XmlRootElement(name = "Clients")
public class xmlClientRepo {
    ArrayList<Client> clientList = new ArrayList<>();

    xmlClientRepo(){}

    @XmlElement(name = "Client")
    ArrayList<Client> getClientList(){
        return this.clientList;
    }

    public void setBookList(ArrayList<Client> b){
        this.clientList = b;
    }


}
