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

    private Jefe(String dni, String telefono, String nombre, String apellido, String direccion) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
    	
        super(dni, telefono, nombre, apellido, direccion);
      
    }

    public static Jefe getInstancia(String dni, String telefono, String nombre, String apellido, String direccion) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
    	
        if (instancia == null) {
        	
            instancia = new Jefe(dni, telefono, nombre, apellido, direccion);      
        }
        
        return instancia;
    }

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

    @Override
    public void trabajoRealizado() {
    	
    }
}
