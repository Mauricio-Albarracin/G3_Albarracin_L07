package Exceptions;

public class ItemDuplicated extends Exception {
    //Constructor 
    public ItemDuplicated(String msg){
        super(msg);
    }
    public ItemDuplicated(){
        super();
    }
}
