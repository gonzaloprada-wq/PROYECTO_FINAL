package modelo;

import excepciones.DniLongitudInvalidaException;

import excepciones.TlfLongitudInvalidaException;

/*
 * Representa un Empleado que trabaja en una Tienda
 * 
 * @author Gonzalo Prada
 * @version 1.0
 * 
 * */

public class deTienda extends Empleados {

	//Cantidad de ventas realizadas por el trabajador
	private int numeroVentas = 0;

	//cantidad dinero generado a la tienda mediante sus ventas
	private double dineroVentas = 0;

	
	/*====================CONSTRUCTORES=======================================================*/
	
	public deTienda(String dni, String telefono, String nombre, String apellido, String direccion)
			throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
		super(dni, telefono, nombre, apellido, direccion);
	}


	public deTienda(String[] datosFichero) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
		
	    super(datosFichero[0], datosFichero[1], datosFichero[2], datosFichero[3], datosFichero[4]);
	    	    
	    
	    setSalarioBruto(Double.parseDouble(datosFichero[6]));
	    
	    setDiasDeVacacionesRestantes(Integer.parseInt(datosFichero[7]));
	    
	    setTieneSeguro(Boolean.parseBoolean(datosFichero[8]));
	    
	    setEstaDePrueba(Boolean.parseBoolean(datosFichero[9]));
	    
	    setFechaInicio(datosFichero[10]);
	    
	    setTrabajando(Boolean.parseBoolean(datosFichero[11]));
	    
	    setDiasTrabajados(Integer.parseInt(datosFichero[12]));
	    
	    setNumeroVentas(Integer.parseInt(datosFichero[13]));
	    
	    setDineroVentas(Double.parseDouble(datosFichero[14]));
	    
	}
	

	public deTienda() {
	}	
	
	/*======================     FIN DE LOS CONSTRUCTORES         =============================*/
	
	
	
	//Gets y sets
	public int getNumeroVentas() {

		return numeroVentas;
	}

	public void setNumeroVentas(int numeroVentas) {

		this.numeroVentas = numeroVentas;
	}

	public double getDineroVentas() {
		return dineroVentas;
	}

	public void setDineroVentas(double dineroVentas) {
		this.dineroVentas = dineroVentas;
	}

	
	/**
	 * Imprime una ficha del trabajo realizado por dicho usuario al que se instancia
	 * 
	 * @return void
 	 * 
	 */
	@Override
	public void trabajoRealizado() {

		System.out.println("El empleado: " + getNombre() + " " + getApellido() + ": ");
		System.out.println("------------------------------------------------- ");
		System.out.println("Numero de ventas: " + getNumeroVentas() + " | Dinero ganado con las ventas: "
				+ getDineroVentas() + "| Dias Trabajados: " + getDiasTrabajados());
	}
	
	/**
	 * Mediante una funcion importado de una interfaz sube el numero de ventas y dineroGenerado 
	 * 
	 * @param precio: es el precio de un articulo que despues se le incrementara al valor dineroGenerado
	 * 
	 * @return void
 	 * 
	 */
	@Override
	public void Incrementar(double precio) {

		setNumeroVentas(getNumeroVentas() + 1);

		setDineroVentas(getDineroVentas() + precio);
	}

}
