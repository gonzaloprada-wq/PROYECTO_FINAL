package modelo;

import java.util.Objects;

import excepciones.ProveedorBloqueadoException;
import excepciones.TlfLongitudInvalidaException;
/*
 * Representa a un proveedor de instrumentos.
 * Cuenta con todos los datos necesarios para su contacto
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public class Proveedor implements Bloqueable {

	private String nombre;

	private String ubicacion;

	private String telefono;

	private String correElectronico;

	private Boolean bloqueado = false;

    /*====================CONSTRUCTORES=======================================================*/

	public Proveedor(String nombre, String telefono) throws TlfLongitudInvalidaException {
		setNombre(nombre);
		setTelefono(telefono);
	}

	public Proveedor(String nombre, String ubicacion, String telefono, String correElectronico)
			throws TlfLongitudInvalidaException {

		setNombre(nombre);
		setTelefono(telefono);
		setUbicacion(ubicacion);
		setCorreElectronico(correElectronico);
	}
		
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

	//Sets y gets
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws TlfLongitudInvalidaException {

		if (telefono.length() != 9) {

			throw new TlfLongitudInvalidaException();

		}

		this.telefono = telefono;
	}

	public String getCorreElectronico() {
		return correElectronico;
	}

	public void setCorreElectronico(String correElectronico) {
		this.correElectronico = correElectronico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(telefono);
	}

	
	//Aqui un equals comun que detectan si son iguales mediante el numero de telefono
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		return Objects.equals(telefono, other.telefono);
	}

	@Override 
	public String toString() { 
		
		return  "Proveedor [Nombre=" + nombre + " | Ubicación=" + ubicacion + " | Tel="
		+ telefono + " | Email=" + correElectronico + "]"; 
		
	}
	
	 /**
     * Esta funcion realiza un bloqueo al proveedor, en el caso de que se quiera cortar relacion con el.
     * Esto convierte el bloquedo en false (boolean)
     *
     * @param Ninguno.
     * 
     * @return void
     */
	@Override
	public void Bloquear() throws ProveedorBloqueadoException {

		if (this.bloqueado) {

			throw new ProveedorBloqueadoException();

		} else {

			this.bloqueado = true;

		}

	}

}
