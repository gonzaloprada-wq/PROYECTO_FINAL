package servicio;

import excepciones.DniLongitudInvalidaException;
import excepciones.TlfLongitudInvalidaException;
import modelo.Empleados;
import modelo.deAlmacen;
import modelo.deTienda;

/*
 * Clase que facilita la creacion de los empleados en base a un objeto
 * 
 * @author Gonzalo Prada
 * @version 1.0
 * 
 * */

public class FabricaEmpleados {
	
	 /**
     * Crea dicho empleado tras pasarle un array con los datos
     *
     * @param T Elemento.
     * 
     * @return void
     */
	public static Empleados crear(String[] datos) {

		//Tipò de empleados es igual a datos[5] porque dicho espacio da lugar al tipo de empleado que es (almacen, tienda)
	    String tipoEmpleado = datos[5];

	    Empleados nuevo = null;

	    try {

	        if (tipoEmpleado.equals("deTienda")) {

	            nuevo = new deTienda(datos);

	        } else if (tipoEmpleado.equals("deAlmacen")) {

	            nuevo = new deAlmacen(datos);

	        }

	    } catch (Exception e) {

	        System.out.println("Error" + e.getMessage());

	    }

	    return nuevo;
	}
	
}