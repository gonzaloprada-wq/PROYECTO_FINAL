package modelo;

import java.util.Objects;
/**
 * Clase abstracta que representa a un Articulo del sistema.
 * Contiene los datos y comportamientos comunes a todos los Artiuclos/objetos.
 * Es extendida por dePersonal (solo para el personal)
 * O por deCatalogo (para su venta)
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public abstract class Articulo {

	
	protected String serialNumber;
	
	protected String marca;
	
	protected String nombre;
	
	protected String modelo;
	
	protected Double precio;
	
	protected String fechaAdquisicion;
	
	protected Boolean enCaja;

	   /*====================CONSTRUCTORES=======================================================*/
	
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
	
    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

	///GETS Y SETS
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

	  /**ABSTRACTO
     * Imprime la localizacion del dicho objeto mediante consola
     * 
     * @param Ninguno
     * 
     * @return void
     */
	public abstract void imprimirLocalizacion();
	

	
	@Override public String toString() { 
		
		return "Articulo [SN=" + serialNumber + " | " + marca + " " + nombre + " (" + modelo + ") "
				+ "| Precio=" + precio + " | Fecha=" + fechaAdquisicion + " | EnCaja=" + enCaja + "]"; }

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}
	  /**
     * Devuelve un boolean (true si es igual o false si no) dependiendo si dos objetos son el mismo
     * Esto se determina mediante el S/N
     * 
     * @param Obj : el objeto que se quiero comprobar si es igual
     * 
     * @return boolean: true si es igual, false si no
     */
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
