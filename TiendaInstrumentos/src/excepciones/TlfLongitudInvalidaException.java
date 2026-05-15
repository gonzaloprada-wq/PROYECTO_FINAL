package excepciones;

public class TlfLongitudInvalidaException extends Exception {
	
    public TlfLongitudInvalidaException() {
    	
       super("ERROR!-La longitud debe ser 9!");
        
    }
}