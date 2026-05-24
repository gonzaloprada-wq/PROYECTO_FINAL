package modelo;

import excepciones.DniLongitudInvalidaException;
import excepciones.TlfLongitudInvalidaException;
/*
 * Representa al Jefe del sistema, implementado como un Singleton (en la memoria mas información
 * para garantizar que solo exista una unica instancia en toda la aplicacion.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class Jefe extends Personal {

    private static Jefe instancia = null;

    private String contraseña = "admin1234";

    /*====================CONSTRUCTORES=======================================================*/
    
    
    private Jefe(String dni, String telefono, String nombre, String apellido, String direccion) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
    	
        super(dni, telefono, nombre, apellido, direccion);
      
    }
    
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

    

   //get y sets
    public static Jefe getInstancia() {
    	
        return instancia;
    }

    public String getContraseña() {
    	
        return contraseña;
    }

    public void setContraseña(String contraseña) {
    	
        this.contraseña = contraseña;
    }

    public String getNombreEmpresarial() {
    	
        return nombre;
    }

    public void setNombreEmpresarial(String nombre) {
    	
        this.nombre = nombre;
        
    }

    /**
     * Esta funcion es la que realiza el singleton (funcion sacada y versionada de canal de youtube
     *
     * @param dias Numero de dias de vacaciones a consumir.
     * 
     * @return devuelve una instancia del Objeto Jefe, como es un sigleton devuelve la misma ya creada
     */
    public static Jefe getInstancia(String dni, String telefono, String nombre, String apellido, String direccion) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
    	
        if (instancia == null) {
        	
            instancia = new Jefe(dni, telefono, nombre, apellido, direccion);      
        }
        
        return instancia;
    }
    
 
    @Override
    public void trabajoRealizado() {

    	System.out.println("Dicho usuario es jefe.");
    	
    }
}
