package Repository;

import Domain.Client;

import Domain.Entity;
import Domain.ValidatorException;

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventWriter;


public class xmlRepository<ID,T extends Entity<ID>> extends InMemoryRepo<ID,T>{
    private String fileName;
    private OutputStream out;
    public xmlRepository(String fileName) throws FileNotFoundException {
        this.fileName=fileName;

        //this.read_from_file(this.fileName);
    }

    public void read_from_file(String fileName){
        try{
            FileInputStream fis=new FileInputStream(new File(fileName));
            XMLDecoder decoder=new XMLDecoder(fis);
            this.entities.stream().forEach(e->{
                e= (T) decoder.readObject();
                this.entities.add(e);
            });
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write_to_file() throws IOException, ParserConfigurationException, JAXBException {

        /*try{
            FileOutputStream fos=new FileOutputStream(new File(fileName));
            XMLEncoder encoder=new XMLEncoder(fos);
            this.entities.stream().forEach(e->encoder.writeObject(e));
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        this.out = new FileOutputStream(this.fileName);
        for (Entity e : this.entities){
            JAXBContext jaxbContext;
            if(e instanceof Domain.Book) {
                 jaxbContext = JAXBContext.newInstance(Domain.Book.class);
            }
            else{
                jaxbContext = JAXBContext.newInstance(Domain.Client.class);
            }
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(e, out);
        }

        this.out.close();



    }

    @Override
    public Optional<T> findOne(ID id) throws ValidatorException {
        return super.findOne(id);
    }

    @Override
    public Iterable<T> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        Optional<T> E = super.save(entity);
        //this.write_to_file();
        return E;
    }

    @Override
    public Optional<T> delete(ID id) throws IOException, ParserConfigurationException, JAXBException {
        Optional<T> E = super.delete(id);
       // this.write_to_file();
        return E;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, IOException, ParserConfigurationException, JAXBException {
        Optional<T> E =super.update(entity);
      //  this.write_to_file();
        return E;
    }
}
