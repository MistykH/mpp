package com.company;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;
import Repository.InMemoryRepo;
import Repository.xmlRepository;
import UI.UI;
import com.sun.xml.bind.v2.model.core.ID;
import tests.testRepo;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, ValidatorException, JAXBException, ParserConfigurationException {
		InMemoryRepo<Integer, Book> bookRepo = new InMemoryRepo();
		InMemoryRepo<Integer, Client> clientRepo = new InMemoryRepo();

		Controller controller = new Controller(bookRepo,clientRepo);
		UI ui = new UI(controller);
		testRepo test = new testRepo();
		//test.test_all();
		xmlRepository<Integer,Book,Client> xmlRepo = new xmlRepository<Integer,Book,Client>("E:\\untitled8\\books.xml","E:\\untitled8\\clients.xml",bookRepo,clientRepo);
		xmlRepo.read_from_file();
		ui.main();

		xmlRepo.write_to_file();



	}
}
