package modelo;

public class deViento extends Instrumento {
	/*
	 * Clase que representa un instrumento de viento.
	 * Contiene los datos y avisos que debe tener un comprados sobre el instrumento
	 * Es extendida por Instrumento
	 *
	 * @author Gonzalo Prada
	 * @version 1.0
	
	 */
	private String material;
	
	   /*====================CONSTRUCTORES=======================================================*/

	
	public deViento(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, String material) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		
		setMaterial(material);
		
	}

    /*======================  FIN DE LOS CONSTRUCTORES  =============================*/

	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	  /**
     * Imprime un aviso que se necesita imprimir para el posible comprador
     * 
     * @param Ninguno
     * 
     * @return void (se imprime por consola)
     */
	@Override
	public void imprimirAviso() {

		System.out.println("Este instrumento esta hecho de: " +getMaterial());
		
	}

	@Override public String toString() { 
		
		return super.toString() + " deViento [Material: " + material + "]"; 
		
	}
	
	

}
