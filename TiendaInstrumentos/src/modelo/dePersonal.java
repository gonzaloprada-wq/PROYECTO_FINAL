package modelo;

public class dePersonal extends Articulo {

	protected String nombreDueño;

	public dePersonal(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, String nombreDueño) {
		
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja);
		
		this.nombreDueño = nombreDueño;
		
	}

	
	public String getNombreDueño() {
		
		return nombreDueño;
		
	}

	public void setNombreDueño(String nombreDueño) {
		
		this.nombreDueño = nombreDueño;
		
	}

	@Override
	public void imprimirLocalizacion() {

		System.out.println("Mesa del Dueño: " + getNombreDueño());

	}
	
}
