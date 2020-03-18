package Repository;

import Domain.Client;
import Domain.Book;
import Domain.Entity;
import Domain.ValidatorException;

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventWriter;


public class xmlRepository<ID,T extends Entity<ID>, D extends Entity<ID>>{
    private String fileNameBooks;
    private String filenameClients;
    private OutputStream out;
    private InputStream in;
    private InMemoryRepo<ID,T> bookRepo;
    private InMemoryRepo<ID,D> clientRepo;
    public xmlRepository(String fileNameBooks,String fileNameClients, InMemoryRepo<ID, T> bookRepo, InMemoryRepo<ID, D> clientRepo) throws FileNotFoundException {
        this.fileNameBooks=fileNameBooks;
        this.filenameClients = fileNameClients;
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;

        //this.read_from_file(this.fileName);
    }

    public xmlRepository() {
    }

    public void read_from_file_books() throws IOException, JAXBException {
        this.in = new FileInputStream(this.fileNameBooks);
        JAXBContext jaxbContext;

        jaxbContext = JAXBContext.newInstance(xmlBookRepo.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

        xmlBookRepo bookrepoxml = new xmlBookRepo();

        //  jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        bookrepoxml =(xmlBookRepo) jaxbMarshaller.unmarshal(in);
        this.bookRepo.setEntities((ArrayList<T>) bookrepoxml.getBookList());
    }

    public void read_from_file_clients() throws IOException, JAXBException {
        this.in = new FileInputStream(this.filenameClients);
        JAXBContext jaxbContext;

        jaxbContext = JAXBContext.newInstance(xmlClientRepo.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

        xmlClientRepo clientrepoxml = new xmlClientRepo();

        //  jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        clientrepoxml =(xmlClientRepo) jaxbMarshaller.unmarshal(in);
        this.clientRepo.setEntities((ArrayList<D>) clientrepoxml.getClientList());
    }

    public void write_to_file_clients() throws IOException, ParserConfigurationException, JAXBException {
        this.out = new FileOutputStream(this.filenameClients);
        JAXBContext jaxbContext;



        jaxbContext = JAXBContext.newInstance(xmlClientRepo.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        xmlClientRepo clientrepoxml = new xmlClientRepo();
        clientrepoxml.setBookList((ArrayList<Client>)clientRepo.getEntities());
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(clientrepoxml, out);
        this.out.close();

    }

    public void write_to_file_books() throws IOException, ParserConfigurationException, JAXBException {
       this.out = new FileOutputStream(this.fileNameBooks);
        JAXBContext jaxbContext;



        jaxbContext = JAXBContext.newInstance(xmlBookRepo.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            xmlBookRepo bookrepoxml = new xmlBookRepo();
        bookrepoxml.setBookList((ArrayList<Book>)bookRepo.getEntities());
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(bookrepoxml, out);


        this.out.close();

    }

    public void write_to_file() throws ParserConfigurationException, JAXBException, IOException {
        this.write_to_file_books();
        this.write_to_file_clients();
    }

    public void read_from_file() throws IOException, JAXBException {
        this.read_from_file_books();
        this.read_from_file_clients();
    }


}
