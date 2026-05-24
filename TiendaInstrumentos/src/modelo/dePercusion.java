package modelo;

import java.util.Objects;

public class dePercusion extends Instrumento {
	/**
	 * Clase que representa un instrumento de percusion.
	 * Contiene los datos y avisos que debe tener un comprados sobre el instrumento
	 * Es extendida por Instrumento
	 *
	 * @author Gonzalo Prada
	 * @version 1.0
	
	 */
	private boolean necesitaBaqueta;

	/*====================CONSTRUCTORES=======================================================*/
	
	public dePercusion(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, boolean necesitaBaqueta) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		setNecesitaBaqueta(necesitaBaqueta);
	}

    /*======================  FIN DE LOS CONSTRUCTORES =============================*/

	//Sets y Gets
	public boolean isNecesitaBaqueta() {
		
		return necesitaBaqueta;
		
	}

	public void setNecesitaBaqueta(boolean necesitaBaqueta) {
		
		this.necesitaBaqueta = necesitaBaqueta;
		
	}
	
	@Override
	public void imprimirAviso() {

		if (isNecesitaBaqueta()) {

			System.out.println("Este instrumento necesita baquetas para su uso.");

		} else {

			System.out.println("Este instrumento no necesita baqueta para su uso.");

		}

	}

	@Override public String toString() { 
		
		return super.toString() + " dePercusion [¿Baqueta?: " + necesitaBaqueta; 
		
	}


}
