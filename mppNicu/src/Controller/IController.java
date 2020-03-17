package Controller;

import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;

import java.util.ArrayList;
import java.util.List;

public interface IController {
    /**
     * @param bookId
     * @param clientId
     * @return true if book was bought, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_buy_book(int bookId, int clientId) throws ValidatorException;

    /**
     *
     * @param id
     * @param where
     * @return true if id is found and false otherwhise
     * @throws ValidatorException
     */
    public boolean Controller_find_one(Integer id, String where) throws ValidatorException;


    /**
     *
     * @return list of books
     */
    public List<Book> Controller_get_books();


    /**
     *
     * @return list of clients
     */
    public List<Client> Controller_get_clients();


    /**
     *
     * @param book
     * @return true if book was added, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_save_book(Book book) throws ValidatorException;


    /**
     *
     * @param client
     * @return true if client was saved, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_save_client(Client client) throws ValidatorException;

    /**
     *
     * @param id
     * @return true if the book was deleted, false otherwise
     */
    public boolean Controller_delete_book(Integer id);

    /**
     *
     * @return the list of clients sorted by the money spent
     */
    public ArrayList<Client> sort_clients();

    /**
     *
     * @param id
     * @return true if the client was deleted, false otheriwse
     */
    public boolean Controller_delete_client(Integer id);


    /**
     *
     * @param book
     * @return true if book was updated, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_update_book(Book book) throws ValidatorException;

    /**
     *
     * @param client
     * @return true if client was updated, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_update_client(Client client) throws ValidatorException;


    /**
     *
     * @param name
     * @return a list of clients filtered by the parameter
     */
    public ArrayList<Client> Controller_filter_by_name(String name);
}
