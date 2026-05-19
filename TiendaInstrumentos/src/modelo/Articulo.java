package modelo;

import java.util.Objects;

public abstract class Articulo {

	protected String serialNumber;
	
	protected String marca;
	
	protected String nombre;
	
	protected String modelo;
	
	protected Double precio;
	
	protected String fechaAdquisicion;
	
	protected Boolean enCaja;

	
	
	public Articulo(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja) {
		
		this.serialNumber = serialNumber;
		
		this.marca = marca;
		
		this.nombre = nombre;
		
		this.modelo = modelo;
		
		this.precio = precio;
		
		this.fechaAdquisicion = fechaAdquisicion;
		
		this.enCaja = enCaja;
		
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(String fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Boolean getEnCaja() {
		return enCaja;
	}

	public void setEnCaja(Boolean enCaja) {
		this.enCaja = enCaja;
	}

	public abstract void imprimirLocalizacion();
	
	@Override
	public String toString() {
		return "Articulo [serialNumber=" + serialNumber + ", marca=" + marca + ", nombre=" + nombre + ", modelo="
				+ modelo + ", precio=" + precio + ", fechaAdquisicion=" + fechaAdquisicion + ", enCaja=" + enCaja + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(serialNumber, other.serialNumber);
	}
	
	
	
	
}
