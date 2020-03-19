package Repository;

import Domain.Book;
import Domain.Client;
import Domain.Entity;

import java.io.*;

public class FileRepository<ID,T extends Entity<ID>> extends InMemoryRepo<ID,T> {
    private String fileName;
    private String type;

    public FileRepository(String fileName, String type) {
        this.fileName = fileName;
        this.type = type;

    }

    public void read_from_file() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this.fileName));
            String line;
            while ((line = br.readLine()) != null) {

                if(this.type.equals("books")){
                    String token[]=line.split(",");
                    Entity<ID> entity= (Entity<ID>) new Book(Integer.parseInt(token[0]),token[1],token[2],Integer.parseInt(token[3]),Integer.parseInt(token[4]));
                    this.entities.add((T) entity);
                }
                else{
                    String[] token1=line.split(",");
                    Entity<ID> entity= (Entity<ID>) new Client(Integer.parseInt(token1[0]),token1[1]);
                    if(entity instanceof Client){
                        ((Client) entity).add_money_spent(Integer.parseInt(token1[2]));

                        if(token1.length>=4) {

                            String token2[] = token1[3].split(";");
                            for (String books : token2) {
                                String attributes[] = books.split(" ");
                                Book book = new Book(Integer.parseInt(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
                                ((Client) entity).add_book(book);
                            }

                        }
                    }
                    this.entities.add((T) entity);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void write_to_file(){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(this.fileName));
            if(this.type.equals("books")){
                for(Entity<ID> e:this.entities){
                    if(e instanceof Book){
                        bw.write(Integer.toString(((Book) e).getid()));
                        bw.write(",");

                        bw.write(((Book) e).getAutorName());
                        bw.write(",");

                        bw.write(((Book) e).getTitle());
                        bw.write(",");

                        bw.write(Integer.toString(((Book) e).getYear()));
                        bw.write(",");

                        bw.write(Integer.toString(((Book) e).getPrice()));

                        bw.newLine();

                    }


                }
            }
            else{
                for(Entity<ID> e:this.entities){
                    if(e instanceof Client) {
                        String client="";
                        client+=((Client) e).getId();
                        client+=",";

                        client+=((Client) e).getFullName();
                        client+=",";

                        client+=((Client) e).get_money_spent();
                        client+=",";

                        String books="";
                        for(Book b:((Client) e).get_books_bought()){
                            books+=b.getid();
                            books+=" ";

                            books+=b.getAutorName();
                            books+=" ";

                            books+=b.getTitle();
                            books+=" ";

                            books+=b.getYear();
                            books+=" ";

                            books+=b.getPrice();
                            books+=" ";

                            books+=";";


                        }
                        /*if(books.equals("")){
                            client=client.substring(0,client.length()-1);

                        }
                        else {
                            books = books.substring(0, books.length() - 1);

                        }*/
                        bw.write(client);
                        bw.write(books);
                        bw.newLine();

                    }
                }

        }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

