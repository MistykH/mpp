package com.company;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Domain.Validator;
import Domain.ValidatorException;
import Repository.FileRepository;
import Repository.InMemoryRepo;
import Repository.xmlRepository;
import UI.UI;
import com.sun.xml.bind.v2.model.core.ID;
import tests.testRepo;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception, ValidatorException {
		int repoTYpe;
		System.out.println("What repo you wanna use? 1-XML  2-Text");
		Scanner scan = new Scanner(System.in);
		Validator validator=new Validator();
		repoTYpe=validator.validate1OR2();
		if(repoTYpe==1) {
			InMemoryRepo<Integer, Book> bookRepo = new InMemoryRepo();
			InMemoryRepo<Integer, Client> clientRepo = new InMemoryRepo();

			Controller controller = new Controller(bookRepo, clientRepo);
			UI ui = new UI(controller);
			testRepo test = new testRepo();
			//test.test_all();
			xmlRepository<Integer, Book, Client> xmlRepo = new xmlRepository<Integer, Book, Client>("C:\\Users\\trasc\\Downloads\\mpp-finished\\books.xml", "C:\\Users\\trasc\\Downloads\\mpp-finished\\clients.xml", bookRepo, clientRepo);
			xmlRepo.read_from_file();
			ui.setBook_id(bookRepo.getSize());
			ui.setClient_id(clientRepo.getSize());
			ui.main();

			xmlRepo.write_to_file();
		}
		else {

			InMemoryRepo<Integer, Book> booksRepo = new FileRepository<Integer, Book>("C:\\Users\\trasc\\Downloads\\mpp-finished\\src\\books.txt", "books");
			InMemoryRepo<Integer, Client> clientsRepo = new FileRepository<Integer, Client>("C:\\Users\\trasc\\Downloads\\mpp-finished\\src\\clients.txt", "clients");
			Controller ctrl2 = new Controller(booksRepo, clientsRepo);
			UI ui=new UI(ctrl2);
			if(booksRepo instanceof FileRepository && clientsRepo instanceof FileRepository){
				((FileRepository<Integer, Book>) booksRepo).read_from_file();
				((FileRepository<Integer, Client>) clientsRepo).read_from_file();
				ui.setBook_id(booksRepo.getSize());
				ui.setClient_id(clientsRepo.getSize());

			}
			ui.main();
			if(booksRepo instanceof FileRepository && clientsRepo instanceof FileRepository){
				((FileRepository<Integer, Book>) booksRepo).write_to_file();
				((FileRepository<Integer, Client>) clientsRepo).write_to_file();
			}


		}


	}
}
