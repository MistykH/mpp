package Controller;

import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;
import Repository.Repository;

import java.util.List;

public class Controller {
    private Repository<Integer, Book> bookRepository;
    private Repository<Integer, Client> clientRepository;


    public Controller(Repository<Integer, Book> bookRepository, Repository<Integer, Client> clientRepository){
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

            clientList.get(clientIndex).add_money_spent(bookList.get(bookIndex).get_price());
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

    public boolean Controller_save_book(Book book) throws ValidatorException {
        try{
            this.bookRepository.save(book);
        } catch ( ValidatorException e) {
            throw e;

        }
        return true;
    }

    public boolean Controller_save_client(Client client) throws ValidatorException {
        try{
            this.clientRepository.save(client);
        } catch (ValidatorException e) {
            throw e;

        }
        return true;
    }

    public boolean Controller_delete_book(Integer id){
        try{
            this.bookRepository.delete(id);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    public boolean Controller_delete_client(Integer id){
        try{
            this.clientRepository.delete(id);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return true;
    }


    public boolean Controller_update_book(Book book) throws ValidatorException {
        try{
            this.bookRepository.update(book);
        } catch (Exception | ValidatorException e) {
            throw e;
        }
        return true;
    }

    public boolean Controller_update_client(Client client) throws ValidatorException {
        try{
            this.clientRepository.update(client);
        } catch (Exception | ValidatorException e) {
            throw e;
        }
        return true;
    }

}
