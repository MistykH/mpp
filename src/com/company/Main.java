package com.company;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;
import Repository.xmlRepository;
import UI.UI;
import tests.testRepo;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ValidatorException, JAXBException, ParserConfigurationException {
	xmlRepository<Integer, Book> bookRepo = new xmlRepository("E:\\untitled8\\books.xml");
	xmlRepository<Integer, Client> clientRepo = new xmlRepository("./clients.xml");

	Controller controller = new Controller(bookRepo,clientRepo);
	UI ui = new UI(controller);
	testRepo test = new testRepo();
	//test.test_all();

	ui.main();

	bookRepo.write_to_file();
	clientRepo.write_to_file();



    }
}
