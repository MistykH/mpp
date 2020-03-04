package UI;

import Controller.Controller;
import Domain.Book;
import Domain.Client;
import Domain.ValidatorException;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Controller controller;
    private int book_id;
    private int client_id;
    public UI(Controller controller){
        this.controller = controller;
        this.book_id = 0;
        this.client_id = 0;
    }

    public void UI_show_menu(){

        System.out.println("1 - add book");
        System.out.println("2 - add client");
        System.out.println("3 - remove book");
        System.out.println("4 - remove client");
        System.out.println("5 - update book");
        System.out.println("6 - update client");
        System.out.println("7 - check book");
        System.out.println("8 - check client");
        System.out.println("9 - show books");
        System.out.println("10 - show clients");
        System.out.println("11 - buy book");
        System.out.println("0 - exit");

    }

    public void UI_add_book(Book book){
        boolean ok = false;
        try{
            ok = this.controller.Controller_save_book(book);
        } catch (ValidatorException e) {
            System.out.println(e.get_message());
        }
        if(ok == true){
            System.out.println("book added");
            this.book_id++;
        }
    }
    public void UI_add_client(Client client){
        boolean ok = false;
        try{
            ok = this.controller.Controller_save_client(client);
        } catch (ValidatorException e) {
            System.out.println(e.get_message());
        }
        if(ok == true){
            System.out.println("client added");
            this.client_id++;
        }
    }

    public void UI_delete_client(Integer id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_delete_client(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("client deleted");
        }
    }

    public void UI_delete_book(Integer id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_delete_book(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book deleted");
        }
    }

    public void UI_update_book(Book book){
        boolean ok = false;
        try{
            ok = this.controller.Controller_update_book(book);
        } catch (IllegalArgumentException | ValidatorException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book updated");
        }
    }

    public void UI_update_client(Client client){
        boolean ok = false;
        try{
            ok = this.controller.Controller_update_client(client);
        } catch (IllegalArgumentException | ValidatorException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("client updated");
        }
    }

    public void UI_check_client(int id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_find_one(id,"client");
        } catch (IllegalArgumentException | ValidatorException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("client exists");
        }
    }

    public void UI_check_book(int id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_find_one(id,"book");
        } catch (IllegalArgumentException | ValidatorException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book exists");
        }
    }

   public void UI_print_clients(){
       List<Client> clientList= this.controller.Controller_get_clients();

       for(Client c : clientList){
           System.out.println(c);
       }

   }

    public void UI_print_books(){
        List<Book> bookList= this.controller.Controller_get_books();

        for(Book b : bookList){
            System.out.println(b);
        }

    }


    public Book UI_read_book(Integer bookId){
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the author name:");
        String title = scan.nextLine();
        System.out.print("enter the title:");
        String authorName = scan.nextLine();
        System.out.print("enter the year:");
        int year = 0;
        int price = 0;
        try {
            year = scan.nextInt();
        } catch (Exception e) {
            System.out.print("invalid year");
        }
        System.out.print("enter the price:");
            try {
                price = scan.nextInt();
            } catch (Exception e) {
                System.out.print("invalid price");
          //  scan.close();
            return null;
        }
        Book returnedBook = new Book(bookId,authorName,title,year,price);
        //scan.close();
        return returnedBook;
    }

    public void UI_buy_book(){
        int bookId = UI_read_int();
        int clientId = UI_read_int();
        boolean ok = false;
        try {
           ok = this.controller.Controller_buy_book(bookId, clientId);
        } catch (IllegalArgumentException | ValidatorException e) {
            System.out.println(e.getMessage());
        }
        if(ok){
            this.UI_delete_book(bookId);
        }
    }

    public Client UI_read_client(Integer clientId){
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the name:");
        String name = scan.nextLine();
        Client returedCliend = new Client(clientId,name);
        return returedCliend;
    }

    public int UI_read_int(){
        Scanner scan = new Scanner(System.in);
        System.out.print("enter an integer:");
        int integerToRead = 0;
        try{
             integerToRead = scan.nextInt();
        } catch (Exception e) {
            System.out.println("not an integer");
         //   scan.close();
            UI_read_int();
        }
      //  scan.close();
        return integerToRead;
    }


    public void main(){

        Scanner scan = new Scanner(System.in);
        while(true){
            this.UI_show_menu();
            int value = scan.nextInt();
            if(value == 0) {
                break;
            }
          else  if(value == 1){
                Book bookToAdd = this.UI_read_book(this.book_id);
                if(bookToAdd != null){
                        this.UI_add_book(bookToAdd);
                }
            }

            else if(value == 2){
                Client clientToAdd = this.UI_read_client(this.client_id);
                this.UI_add_client(clientToAdd);
            }
            else  if(value == 3){
                int id = UI_read_int();
                Integer integer = id;
                UI_delete_book(integer);
            }
            else  if(value == 4){
                int id = UI_read_int();
                Integer integer = id;
                UI_delete_client(integer);
            }
            else  if(value == 5){
                int id = UI_read_int();
                Book bookToUpdate = UI_read_book(id);
                this.UI_update_book(bookToUpdate);
            }
            else if(value == 6){
                int id = UI_read_int();
                Client clientToUpdate = UI_read_client(id);
                this.UI_update_client(clientToUpdate);
            }
            else   if(value == 7){
                int id = UI_read_int();
                this.UI_check_book(id);
            }
            else if(value == 8){
                int id = UI_read_int();
                this.UI_check_client(id);
            }
            else  if(value == 9){
                this.UI_print_books();
            }
            else if(value == 10){
                this.UI_print_clients();
            }
            else if(value == 11){
                this.UI_buy_book();
            }
        }
        scan.close();
    }







}
