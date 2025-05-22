package Exceptions;

public class ExceptionIsEmpty extends Exception{
    public ExceptionIsEmpty(){
        super("Está vacío"); //Constructor del mensaje a mostrar
    }

    public ExceptionIsEmpty(String mensaje){
        super(mensaje); //Constructor que va a mostrar del mensaje
    }
    
}
