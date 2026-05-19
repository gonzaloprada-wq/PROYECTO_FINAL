package modelo;

public class dePercusion extends Instrumento {

	private boolean necesitaBaqueta;

	public dePercusion(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, boolean necesitaBaqueta) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		setNecesitaBaqueta(necesitaBaqueta);
	}

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

}
