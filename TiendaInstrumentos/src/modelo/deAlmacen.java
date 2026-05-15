package modelo;

import excepciones.DniLongitudInvalidaException;

import excepciones.TlfLongitudInvalidaException;

/*
 * Representa un Empleado que trabaja en un almacen
 * 
 * @author Gonzalo Prada
 * @version 1.0
 * 
 * */
public class deAlmacen extends Empleados {

	//cantidad de bultos que ha cargado el empleado
	private int numeroBultos = 0;

	//cantidad de pesoCargado en total
	private double pesoCargado = 0;

	/*
	 * ====================CONSTRUCTORES============================================
	 */

	public deAlmacen(String dni, String telefono, String nombre, String apellido, String direccion)
			throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
		super(dni, telefono, nombre, apellido, direccion);
	}

	public deAlmacen() {

	}

	public deAlmacen(String[] datosFichero) throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
		
	    super(datosFichero[0], datosFichero[1], datosFichero[2], datosFichero[3], datosFichero[4]);
	    
	    
	    setSalarioBruto(Double.parseDouble(datosFichero[6]));
	    
	    setDiasDeVacacionesRestantes(Integer.parseInt(datosFichero[7]));
	    
	    setTieneSeguro(Boolean.parseBoolean(datosFichero[8]));
	    
	    setEstaDePrueba(Boolean.parseBoolean(datosFichero[9]));
	    
	    setFechaInicio(datosFichero[10]);
	    
	    setTrabajando(Boolean.parseBoolean(datosFichero[11]));
	    
	    setDiasTrabajados(Integer.parseInt(datosFichero[12]));
	    
	    setNumeroBultos(Integer.parseInt(datosFichero[13]));
	    
	    setPesoCargado(Double.parseDouble(datosFichero[14]));
	}
	
	
	
	/*==========FIN DE LOS CONSTRUCTORES=========================================*/

	
	
	
	/*Gets y Sets*/
	
	public int getNumeroBultos() {

		return numeroBultos;

	}

	public void setNumeroBultos(int numeroBultos) {

		this.numeroBultos = numeroBultos;

	}

	public double getPesoCargado() {

		return pesoCargado;

	}

	public void setPesoCargado(double pesoCargado) {

		this.pesoCargado = pesoCargado;

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
		System.out.println("Numero de bultos: " + getNumeroBultos() + " | Peso cargado: " + getPesoCargado()
				+ " kg | Dias Trabajados: " + getDiasTrabajados());
	}
	
	/**
	 * Mediante una funcion importado de una interfaz sube el numero de peso y bultos 
	 * 
	 * @param peso: es el peso de un paquete que despues se le incrementara al valor pesoCargado
	 * 
	 * @return void
 	 * 
	 */
	@Override
	public void Incrementar(double peso) {

		setNumeroBultos(getNumeroBultos() + 1);

		setPesoCargado(getPesoCargado() + peso);

	}
}