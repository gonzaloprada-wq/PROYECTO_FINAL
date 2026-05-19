package modelo;

public class deCuerda extends Instrumento {


	private int numeroCuerdas;
	
	private double calibreCuerdas;
	
	
	public deCuerda(String serialNumber, String marca, String nombre, String modelo, Double precio,
			String fechaAdquisicion, Boolean enCaja, int numeroAlmacen, int numeroPalet, int numeroCuerdas,
			double calibreCuerdas) {
		super(serialNumber, marca, nombre, modelo, precio, fechaAdquisicion, enCaja, numeroAlmacen, numeroPalet);
		setNumeroCuerdas(numeroCuerdas);
		this.calibreCuerdas = calibreCuerdas;
	}


	public int getNumeroCuerdas() {
		return numeroCuerdas;
	}

	public void setNumeroCuerdas(int numeroCuerdas) {
		if(numeroCuerdas<2) {
			
			numeroCuerdas=1;
			
		}
		
		this.numeroCuerdas=numeroCuerdas;
		
	}

	
	public double getCalibreCuerdas() {
		return calibreCuerdas;
	}

	
	public void setCalibreCuerdas(double calibreCuerdas) {
		this.calibreCuerdas = calibreCuerdas;
	}

	
	@Override
	public void imprimirAviso() {

		System.out.println("Este instrumento usa "+getNumeroCuerdas()+ " cuerdas, con un calibre preferido de "+getCalibreCuerdas());
		
	}

	@Override
	public String toString() {
		return "deCuerda [numeroCuerdas=" + numeroCuerdas + ", calibreCuerdas=" + calibreCuerdas + "]";
	}

	
	
}
