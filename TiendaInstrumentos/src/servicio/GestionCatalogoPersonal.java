package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelo.dePersonal;

public class GestionCatalogoPersonal {
	

	private ArrayList<dePersonal> listadelPersonal = new ArrayList<>();
	

	private static final String FICHERO_PERSONAL = "src/repositorio/personal.txt";
	
	

	public GestionCatalogoPersonal() {
		
	}

	
	
	public ArrayList<dePersonal> getListaPersonal() {
		
		return listadelPersonal;
		
	}

	
	
	public void setListaPersonal(ArrayList<dePersonal> listadelPersonal) {
		
		this.listadelPersonal = listadelPersonal;
		
	}



	
	
	public void añadirArticulo(dePersonal articulo) {
		
		if (buscarArticulo(articulo.getSerialNumber()) == null) {
			
			listadelPersonal.add(articulo);
			
		}
	}

	public void eliminarArticulo(String serialNumber) {
		listadelPersonal.removeIf(articulo -> articulo.getSerialNumber().equals(serialNumber));
	}

	public dePersonal buscarArticulo(String serialNumber) {
		
		dePersonal ejemplo=null;
		
		for (dePersonal articuloActual : listadelPersonal) {
			
			if (articuloActual.getSerialNumber().equals(serialNumber)) {
				
				ejemplo = articuloActual;
			}
			
		}
		
		return ejemplo;
	}

	public void imprimirTodos() {
		
		if (listadelPersonal.size() != 0) {
			
			for (dePersonal articulo : listadelPersonal) {
				
				System.out.println(articulo.toString());
				
			}
			
		} else {
			System.out.println("No hay articulos de personal registrados.");
		}
	}


	
	public double calcularPrecioTotal() {
		
	   double precioTotal= listadelPersonal.stream()
	            .map(articulo -> articulo.getPrecio())
	            .reduce(0.0, (a, b) -> a + b);
	     
	     return precioTotal;
	}
	// ---------------------------------------------------------------
	// PERSISTENCIA
	// ---------------------------------------------------------------

	public void guardarDatosPersonal() {
		
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_PERSONAL))) {
			
			for (dePersonal articuloActual : listadelPersonal) {
				
				escritor.write(lineaDePersonal(articuloActual));
				
				escritor.newLine();
			}
			
		} catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}

	
	// Este bloque return lo ha hecho la inteliencia artificial "Gemini" para el ahorro de tiempo debido a que es un proceso lento y repetitivo
	private String lineaDePersonal(dePersonal articuloActual) {
		 String lineaFinal=articuloActual.getSerialNumber() + " - " + articuloActual.getMarca() + " - " + articuloActual.getNombre() + " - "
				+ articuloActual.getModelo() + " - " + articuloActual.getPrecio() + " - " + articuloActual.getFechaAdquisicion() + " - "
				+ articuloActual.getEnCaja() + " - " + articuloActual.getNombreDueño();
		 
		 return lineaFinal;
	}
//Fin del bloque
	
	public void implementarDatosPersonal() {
		
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(FICHERO_PERSONAL))) {
			
			String lineaActual;
			
			while ((lineaActual = bufferReader.readLine()) != null) {
				
				String[] datosPErsonales = lineaActual.split(" - ");
				
				reconstruirDePersonal(datosPErsonales);				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}

	private void reconstruirDePersonal(String[] datos) {
		
		try {
			
			dePersonal articulo = new dePersonal(
					
					datos[0],
					
					datos[1], 
					
					datos[2], 
					
					datos[3], 
					
					Double.parseDouble(datos[4]),
			
					datos[5], 
					
					Boolean.parseBoolean(datos[6]),
					
					datos[7]);
			
			listadelPersonal.add(articulo);
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
}