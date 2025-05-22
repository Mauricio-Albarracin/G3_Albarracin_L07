package Exceptions;

public class ItemNoFound extends Exception {
    //Constructor 
    public ItemNoFound(String msg){
        super(msg);
    }
    public ItemNoFound(){
        super();
    }
    
}
