package excepciones;

public class DniLongitudInvalidaException extends Exception {
	
    public DniLongitudInvalidaException() {
    	
       super("ERROR!-La longitud debe ser 9!");
        
    }
}