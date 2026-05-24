package modelo;

import java.util.Objects;

import excepciones.DniLongitudInvalidaException;
import excepciones.TlfLongitudInvalidaException;

public abstract class Personal {

	protected String dni;

	protected String telefono;

	protected String nombre;

	protected String apellido;

	protected String direccion;

	public Personal(String dni, String telefono, String nombre, String apellido, String direccion)
			throws DniLongitudInvalidaException, TlfLongitudInvalidaException {

		setDni(dni);

		setTelefono(telefono);

		setNombre(nombre);

		setApellido(apellido);

		setDireccion(direccion);

	}
	
	public Personal(Personal otro) {
		
	    this.dni = otro.dni;
	    
	    this.telefono = otro.telefono;
	    
	    this.nombre = otro.nombre;
	    
	    this.apellido = otro.apellido;
	    
	    this.direccion = otro.direccion;
	    
	}

	public Personal() {

	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws DniLongitudInvalidaException {

		if (dni == null || dni.length() != 9) {

			throw new DniLongitudInvalidaException();

		}

		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws TlfLongitudInvalidaException {

		if (telefono.length()!=9){

			throw new TlfLongitudInvalidaException();

		}

		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(dni, telefono);
	}

//por telefono o por dni
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personal other = (Personal) obj;
		return Objects.equals(dni, other.dni) || Objects.equals(telefono, other.telefono);
	}

	public abstract void trabajoRealizado();

	@Override
	public String toString() {
		return "Personal [dni=" + dni + ", telefono=" + telefono + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + "]";
	}

}
