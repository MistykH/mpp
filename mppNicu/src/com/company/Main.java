package com.company;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Repository.Repository;
import Repository.InMemoryRepo;
import UI.UI;

public class Main {

    public static void main(String[] args) {
	Repository<Integer, Book> bookRepo = new InMemoryRepo<Integer, Book>();
	Repository<Integer, Client> clientRepo = new InMemoryRepo<Integer, Client>();

	Controller controller = new Controller(bookRepo,clientRepo);
	UI ui = new UI(controller);
	ui.main();


    }
}
