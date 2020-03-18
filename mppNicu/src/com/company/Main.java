package com.company;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;
import Repository.xmlRepository;
import Repository.InMemoryRepo;
import UI.UI;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ValidatorException {
	xmlRepository<Integer, Book> bookRepo = new xmlRepository("./books.xml");
	xmlRepository<Integer, Client> clientRepo = new xmlRepository("");

	Controller controller = new Controller(bookRepo,clientRepo);
	UI ui = new UI(controller);
	ui.main();


    }
}
