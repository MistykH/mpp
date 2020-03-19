package tests;

import Domain.ValidatorException;
import Repository.InMemoryRepo;
import Domain.Book;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class testRepo {

    public void testRepo(){}

    public void test_all() throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        this.test_add();
        this.test_delete();
        this.test_update();
    }

    public void test_add() throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        InMemoryRepo<Integer, Book> testRepo= new InMemoryRepo<Integer, Book>();
        ArrayList<Book> bookList = new ArrayList<>();
        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 0;

        Book testBook = new Book(1,"authorName","titlu",100,200);

        testRepo.save(testBook);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 1;

        assert testRepo.findOne(1).equals(testBook) == true;

        Book testBook2 = new Book(2,"authorName","titlu",100,200);

        testRepo.save(testBook);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 2;

        assert testRepo.findOne(2).equals(testBook) == true;


        Book testBook3 = new Book(3,"authorName","titlu",100,200);

        testRepo.save(testBook);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 3;

        assert testRepo.findOne(3).equals(testBook) == true;




    }


    public void test_update() throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        InMemoryRepo<Integer, Book> testRepo= new InMemoryRepo<Integer, Book>();
        ArrayList<Book> bookList = new ArrayList<>();
        Book testBook1 = new Book(1,"authorName","titlu",100,200);
        Book testBook2 = new Book(1,"authorName2","titlu2",200,300);
        testRepo.save(testBook1);
        testRepo.update(testBook2);
        bookList = (ArrayList<Book>) testRepo.findAll();
        Book b = bookList.get(0);
        assert b.getAutorName() == "authorName2";
        assert b.getPrice() == 300;
        assert b.getTitle() == "titlu2";
        assert b.getYear() == 200;



        Book testBook3 = new Book(2,"authorName3","titlu3",100,100);
        Book testBook4 = new Book(2,"authorName4","titlu4",400,400);
        testRepo.save(testBook1);
        testRepo.update(testBook4);
        bookList = (ArrayList<Book>) testRepo.findAll();
        b = bookList.get(0);
        assert b.getAutorName() == "authorName3";
        assert b.getPrice() == 400;
        assert b.getTitle() == "titlu4";
        assert b.getYear() == 400;




    }

    public void test_delete() throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        InMemoryRepo<Integer, Book> testRepo= new InMemoryRepo<Integer, Book>();
        ArrayList<Book> bookList = new ArrayList<>();
        Book testBook1 = new Book(1,"authorName","titlu",100,200);
        Book testBook2 = new Book(2,"authorName2","titlu2",200,300);
        Book testBook3 = new Book(3,"authorName3","titlu3",100,100);
        Book testBook4 = new Book(4,"authorName4","titlu4",400,400);
        testRepo.save(testBook1);
        testRepo.save(testBook2);
        testRepo.save(testBook3);
        testRepo.save(testBook4);
        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 4;
        testRepo.delete(4);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 3;
        testRepo.delete(1);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 2;
        testRepo.delete(2);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 1;
        testRepo.delete(3);

        bookList = (ArrayList<Book>) testRepo.findAll();
        assert bookList.size() == 0;

    }


}
