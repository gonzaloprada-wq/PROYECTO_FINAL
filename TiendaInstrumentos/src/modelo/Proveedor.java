package modelo;

import java.util.Objects;

import excepciones.ProveedorBloqueadoException;
import excepciones.TlfLongitudInvalidaException;

public class Proveedor implements Bloqueable {

	private String nombre;

	private String ubicacion;

	private String telefono;

	private String correElectronico;

	private Boolean bloqueado = false;

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
		return "Proveedor [nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono
				+ ", correElectronico=" + correElectronico + "]";
	}

	@Override
	public void Bloquear() throws ProveedorBloqueadoException {

		if (this.bloqueado) {

			throw new ProveedorBloqueadoException();

		} else {

			this.bloqueado = true;

		}

	}

}
