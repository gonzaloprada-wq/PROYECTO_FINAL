package modelo;

public class deViento extends Instrumento {

	private String material;
	
	public deViento(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, String material) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		
		setMaterial(material);
		
	}

	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}


	@Override
	public void imprimirAviso() {

		System.out.println("Este instrumento esta hecho de: " +getMaterial());
		
	}


	@Override
	public String toString() {
		return "deViento [material=" + material + "]";
	}
	
	

}
