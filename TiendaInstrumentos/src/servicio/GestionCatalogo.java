package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import modelo.Articulo;
import modelo.deCatalogo;
import modelo.deCuerda;
import modelo.dePercusion;
import modelo.deViento;

public class GestionCatalogo {

	private HashMap<String, deCatalogo> mapaCatalogo = new HashMap<>();

	private static final String FICHERO_CATALOGO = "src/repositorio/catalogo.txt";

	public GestionCatalogo() {
	}

	public HashMap<String, deCatalogo> getMapaCatalogo() {
		return mapaCatalogo;
	}

	public void setMapaCatalogo(HashMap<String, deCatalogo> mapaCatalogo) {
		this.mapaCatalogo = mapaCatalogo;
	}

	// AQUI EL HE HECHO EL CRUD

	public void añadirArticulo(deCatalogo articulo) {
		if (buscarArticulo(articulo.getSerialNumber()) == null) {
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
		}
	}

	public void eliminarArticulo(String serialNumber) {

		mapaCatalogo.remove(serialNumber);

	}

	public deCatalogo buscarArticulo(String serialNumber) {

		return mapaCatalogo.get(serialNumber);

	}

	public void imprimirTodos() {
		for (deCatalogo articuloActual : mapaCatalogo.values()) {
			System.out.println(articuloActual.toString());
		}
	}

	// Codigo versionado de video canal de youtube "Makigas"
	public void imprimirDisponibles() {

		List<deCatalogo> lista = mapaCatalogo.values().stream()

				.filter(articulo -> !articulo.getEstaReservado())

				.collect(Collectors.toList());

		for (deCatalogo articulo : lista) {

			System.out.println(articulo.toString());

		}

	}

	// Codigo versionado de video canal de youtube "Makigas"
	public double contarDisponibles() {

		return mapaCatalogo.values().stream()

				// Nota personal: (ELIMINAR A LA HORA DE ENTREGAR)
				// Segun el video de youtube filter determina mediante el boolean si es true o
				// false
				// como en este caso el mismo get devuelve boolean es mas sencillo
				.filter(articulo -> !articulo.getEstaReservado())

				.count();
	}

	// PERSISTENCIA

	public void guardarDatosCatalogo() {

		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_CATALOGO))) {

			for (deCatalogo articuloActual : mapaCatalogo.values()) {

				if (articuloActual instanceof deCuerda) {

					escritor.write(lineaDeCuerda((deCuerda) articuloActual));

				} else if (articuloActual instanceof deViento) {

					escritor.write(lineaDeViento((deViento) articuloActual));

				} else if (articuloActual instanceof dePercusion) {

					escritor.write(lineaDePercusion((dePercusion) articuloActual));

				}

			}
		} catch (IOException e) {

			System.out.println("Error al guardar el catalogo: " + e.getMessage());

		}
	}

	
	//Este bloque return lo ha hecho la inteliencia artificial "Gemini" para el ahorro de tiempo debido a que es un proceso lento y repetitivo
	private String datosComunes(deCatalogo articulo, String tipo) {

		return articulo.getSerialNumber() + " - " + articulo.getMarca() + " - " + articulo.getNombre() + " - "
				+ articulo.getModelo() + " - " + articulo.getPrecio() + " - " + articulo.getFechaAdquisicion() + " - "
				+ articulo.getEnCaja() + " - " + articulo.getNumeroAlmacen() + " - " + articulo.getNumeroPalet() + " - "
				+ articulo.getEstaReservado() + " - " + tipo;

	}

	private String lineaDeCuerda(deCuerda articulo) {
		

		String lineaDevolver = datosComunes(articulo, "deCuerda") + " - " + articulo.getNumeroCuerdas() + " - "+ articulo.getCalibreCuerdas();

		return lineaDevolver;
	}

	private String lineaDeViento(deViento articulo) {

		String lineaDevolver = datosComunes(articulo, "deViento") + " - " + articulo.getMaterial();

		return lineaDevolver;
	}

	private String lineaDePercusion(dePercusion articulo) {
		String lineaDevolver = datosComunes(articulo, "dePercusion") + " - " + articulo.isNecesitaBaqueta();

		return lineaDevolver;
	}

	// HASTA AQUI ESTA BIEN EL CODIGO Y ME GUSTA TODO LO DEMAS ME PARECE CIERTAMENTE ACONGLOMERADO POCO LEGIBLE Y MUY NOTORIA LA IA CAMBIALO

	public void implementarDatosCatalogo() {
		try (BufferedReader lector = new BufferedReader(new FileReader(FICHERO_CATALOGO))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] datos = linea.split(" - ");
				if (datos[10].equals("deCuerda")) {
					reconstruirDeCuerda(datos);
				} else if (datos[10].equals("deViento")) {
					reconstruirDeViento(datos);
				} else if (datos[10].equals("dePercusion")) {
					reconstruirDePercusion(datos);
				}
			}
		} catch (IOException e) {
			System.out.println("Error al cargar el catalogo: " + e.getMessage());
		}
	}

	private void reconstruirDeCuerda(String[] datos) {
		try {
			deCuerda articulo = new deCuerda(datos[0], datos[1], datos[2], datos[3], Double.parseDouble(datos[4]),
					datos[5], Boolean.parseBoolean(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]),
					Integer.parseInt(datos[11]), Double.parseDouble(datos[12]));
			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
		} catch (Exception e) {
			System.out.println("Error al reconstruir deCuerda: " + e.getMessage());
		}
	}

	private void reconstruirDeViento(String[] datos) {
		try {

			deViento articulo = new deViento(

					datos[0], datos[1], datos[2], datos[3],

					Double.parseDouble(datos[4]), datos[5],

					Boolean.parseBoolean(datos[6]),

					Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), datos[11]);

			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
		} catch (Exception e) {
			System.out.println("Error al reconstruir: " + e.getMessage());
		}
	}

	private void reconstruirDePercusion(String[] datos) {
		try {
			dePercusion articulo = new dePercusion(datos[0], datos[1], datos[2], datos[3], Double.parseDouble(datos[4]),
					datos[5], Boolean.parseBoolean(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]),
					Boolean.parseBoolean(datos[11]));
			articulo.setEstaReservado(Boolean.parseBoolean(datos[9]));
			mapaCatalogo.put(articulo.getSerialNumber(), articulo);
		} catch (Exception e) {
			System.out.println("Error al reconstruir dePercusion: " + e.getMessage());
		}
	}
}