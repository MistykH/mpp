package Controller;

import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;
import Repository.xmlRepository;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements IController{
    private xmlRepository<Integer,Book> bookRepository;
    private xmlRepository<Integer, Client> clientRepository;


    public Controller(xmlRepository<Integer, Book> bookRepository, xmlRepository<Integer, Client> clientRepository){
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }


    public boolean Controller_buy_book(int bookId, int clientId) throws ValidatorException {
        if(bookId < 0){
            throw new ValidatorException("invalid id");
        }
        if(clientId < 0){
            throw new ValidatorException("invalid id");
        }
        List<Book> bookList = this.Controller_get_books();
        List<Client> clientList = this.Controller_get_clients();
        Book bookToBuy = null;
        Client client = null;
        int bookIndex = 0;
        int clientIndex = 0;
        for(Book b : bookList) {
            if (b.getId() == bookId) {
                bookToBuy = b;
                bookIndex = bookList.indexOf(b);
                break;
            }
        }
        for(Client c: clientList) {
            if (c.getId() == clientId) {
                client = c;
                clientIndex = clientList.indexOf(c);
                break;
            }
        }
            if(client == null || bookToBuy == null){
                throw new IllegalArgumentException("invalid ids");
            }

            clientList.get(clientIndex).add_money_spent(bookList.get(bookIndex).getPrice());
            clientList.get(clientIndex).add_book(bookList.get(bookIndex));

            return true;


    }


    public boolean Controller_find_one(Integer id, String where) throws ValidatorException {
        if(where == "book") {
            try{
                this.bookRepository.findOne(id);
                return true;
            } catch (Exception | ValidatorException e) {
                throw e;

            }
        }

        else if(where == "client"){
            try{
                this.clientRepository.findOne(id);
                return true;
            } catch (Exception | ValidatorException e) {
                throw e;

            }
        }
        return true;
    }

    public List<Book> Controller_get_books(){
        return (List<Book>) this.bookRepository.findAll();
    }

    public List<Client> Controller_get_clients(){
        return (List<Client>) this.clientRepository.findAll();
    }

    public boolean Controller_save_book(Book book) throws ValidatorException, FileNotFoundException, ParserConfigurationException, JAXBException {
        try{
            this.bookRepository.save(book);
        } catch (ValidatorException | FileNotFoundException | ParserConfigurationException | JAXBException e) {
            throw e;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean Controller_save_client(Client client) throws ValidatorException, FileNotFoundException, ParserConfigurationException, JAXBException {
        try{
            this.clientRepository.save(client);
        } catch (ValidatorException | FileNotFoundException | ParserConfigurationException | JAXBException e) {
            throw e;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean Controller_delete_book(Integer id) throws IOException, ParserConfigurationException, JAXBException {
        try{
            this.bookRepository.delete(id);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }


    public ArrayList<Client> sort_clients(){
        ArrayList<Client> clientList = (ArrayList<Client>) this.clientRepository.findAll();
        ArrayList<Client> clientListSorted = (ArrayList<Client>) clientList.clone();

       /* while(!ok){
            ok = true;
            for(int i = 0 ; i < clientList.size() - 1; i++){
                if(clientListSorted.get(i).get_money_spent() > clientListSorted.get(i+1).get_money_spent()){
                    Client aux = clientListSorted.get(i);
                    clientListSorted.set(i,clientListSorted.get(i+1));
                    clientListSorted.set(i+1,aux);
                    ok = false;
                }
            }
        }*/



       clientListSorted= (ArrayList<Client>) clientListSorted.stream().sorted(Comparator.comparing(c -> c.get_money_spent())).collect(Collectors.toList());
        return clientListSorted;
    }

    public boolean Controller_delete_client(Integer id) throws FileNotFoundException, ParserConfigurationException, JAXBException {
        try{
            this.clientRepository.delete(id);
        } catch (IllegalArgumentException | FileNotFoundException | ParserConfigurationException | JAXBException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean Controller_update_book(Book book) throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        try{
            this.bookRepository.update(book);
        } catch (Exception | ValidatorException e) {
            throw e;
        }
        return true;
    }

    public boolean Controller_update_client(Client client) throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        try{
            this.clientRepository.update(client);
        } catch (Exception | ValidatorException e) {
            throw e;
        }
        return true;
    }

    public ArrayList<Client> Controller_filter_by_name(String name){
        ArrayList<Client> clientListFiltered = new ArrayList<Client>();

        /*for(Client c : clientList){
            if(c.getFullName().contains(name)){
                clientListFiltered.add(c);
            }
        }*/
        clientListFiltered= (ArrayList<Client>) this.Controller_get_clients().stream().filter(c->c.getFullName().contains(name)).collect(Collectors.toList());
        return clientListFiltered;



    }

}
