package Domain;

public class Validator {


    Validator(){

    }

    public boolean validate_book(Book b){
        if(b.getYear() < 0){
            return false;
        }
        if(b.getPrice() < 0){
            return false;
        }
        return true;
    }

    public boolean validate_client(Client c){
        if(c.get_money_spent() < 0){
            return false;
        }
        if(c.getFullName().isEmpty()){
            return false;
        }
        return true;
    }


}
