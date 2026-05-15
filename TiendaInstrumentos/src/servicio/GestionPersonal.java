	package servicio;
	
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Scanner;
	import excepciones.ContrasenaInvalidaException;
	import excepciones.DniLongitudInvalidaException;
	import excepciones.EmpleadoContratadoException;
	import excepciones.EmpleadoDespedidoException;
	import excepciones.TlfLongitudInvalidaException;
import modelo.Empleados;
import modelo.Jefe;
import modelo.deAlmacen;
import modelo.deTienda;
	
	public class GestionPersonal {
	
		private HashMap<String, Empleados> mapaEmpleados = new HashMap<>();
	
		private static final String FICHERO_EMPLEADOS = "src/repositorio/empleados.txt";
	
		private static final String FICHERO_CONTRASEÑA = "src/recursos/contraseña.txt";
	
		public GestionPersonal() {
	
		}
	
		public HashMap<String, Empleados> getMapaEmpleados() {
	
			return mapaEmpleados;
	
		}
	
		public void setMapaEmpleados(HashMap<String, Empleados> mapaEmpleados) {
	
			this.mapaEmpleados = mapaEmpleados;
	
		}
	
		public void comprobarContraseña() throws ContrasenaInvalidaException {
	
			Scanner scanner = new Scanner(System.in);
	
			System.out.println("Introduce la contraseña actual: ");
	
			String contraseñaIntroducida = scanner.nextLine();
	
			if (!Jefe.getInstancia().getContraseña().equals(contraseñaIntroducida)) {
	
				throw new ContrasenaInvalidaException();
			}
		}
	
		public void cambiarContraseña() {
	
			Scanner scanner = new Scanner(System.in);
	
			String contraseña1;
	
			String contraseña2;
	
			do {
				System.out.println("Introduce la nueva contraseña (deben ser iguales): ");
	
				contraseña1 = scanner.nextLine();
	
				System.out.println("Repite la nueva contraseña: ");
	
				contraseña2 = scanner.nextLine();
	
			} while (!contraseña1.equals(contraseña2));
	
			actualizacionContraseña(contraseña1);
		}
	
		public void actualizacionContraseña(String contraseñaNueva) {
	
			Jefe.getInstancia().setContraseña(contraseñaNueva);
	
			try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_CONTRASEÑA))) {
	
				escritor.write(contraseñaNueva);
	
			} catch (IOException e) {
	
				System.out.println("Error al actualizar la contraseña: " + e.getMessage());
	
			}
		}
	
		public void contratarEmpleado(Empleados nuevoEmpleado) throws EmpleadoContratadoException {
	
			if (buscarEmpleado(nuevoEmpleado.getDni()) == null) {
	
				getMapaEmpleados().put(nuevoEmpleado.getDni(), nuevoEmpleado);
	
			} else {
	
				throw new EmpleadoContratadoException();
	
			}
	
		}
	
		public void despedirEmpleado(String dni) throws EmpleadoDespedidoException {
	
			if (buscarEmpleado(dni) == null) {
	
				throw new EmpleadoDespedidoException();
	
			} else {
	
				getMapaEmpleados().remove(dni);
	
			}
	
		}
	
		public Empleados buscarEmpleado(String dni) {
	
			return mapaEmpleados.get(dni);
	
		}
	
		public void imprimirTodos() {
			for (Empleados empleado : mapaEmpleados.values()) {
				System.out.println(empleado.toString());
			}
		}
	
		public void guardarDatosEmpleados() {
	
			try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_EMPLEADOS))) {
	
				for (Empleados empleado : mapaEmpleados.values()) {
	
					if (empleado instanceof deTienda) {
	
						escritor.write(lineaDeTienda((deTienda) empleado));
	
					} else if (empleado instanceof deAlmacen) {
	
						escritor.write(lineaDeAlmacen((deAlmacen) empleado));
					}
	
					escritor.newLine();
	
				}
	
			} catch (IOException e) {
				System.out.println("Error al guardar los empleados: " + e.getMessage());
			}
		}
	
		private String lineaDeTienda(deTienda empleadoActual) {
	
			/*	
			 * La siguiente linea "lineaActual" (solo linea, logica hecha por autor @Gonzalo Prada)  de datos ha sido construido con la Inteligencia artificial
			 * "Claude" para ahorrar tiempo, en la fecha de 14/05/2026 y reescrito por
			 * autor @Gonzalo Prada | Ultima fecha de modificacion: 14/05/26 (
			 */
			String lineaActual= empleadoActual.getDni() + " - " + empleadoActual.getTelefono() + " - " + empleadoActual.getNombre()
					+ " - " + empleadoActual.getApellido() + " - " + empleadoActual.getDireccion() + " - " + "deTienda"
					+ " - " + empleadoActual.getSalarioBruto() + " - " + empleadoActual.getDiasDeVacacionesRestantes()
					+ " - " + empleadoActual.isTieneSeguro() + " - " + empleadoActual.getEstaDePrueba() + " - "
					+ empleadoActual.getFechaInicio() + " - " + empleadoActual.getIsTrabajando() + " - "
					+ empleadoActual.getDiasTrabajados() + " - " + empleadoActual.getNumeroVentas() + " - "
					+ empleadoActual.getDineroVentas();
			
			return lineaActual;
		}
	
		private String lineaDeAlmacen(deAlmacen empleadoActual) {
			
			/*	
			 * La siguiente linea "lineaActual" (solo linea, logica hecha por autor @Gonzalo Prada)  de datos ha sido construido con la Inteligencia artificial
			 * "Claude" para ahorrar tiempo, en la fecha de 14/05/2026 y reescrito por
			 * autor @Gonzalo Prada | Ultima fecha de modificacion: 14/05/26 (
			 */
			
			String lineaActual= empleadoActual.getDni() + " - " + empleadoActual.getTelefono() + " - " + empleadoActual.getNombre()
					+ " - " + empleadoActual.getApellido() + " - " + empleadoActual.getDireccion() + " - " + "deAlmacen"
					+ " - " + empleadoActual.getSalarioBruto() + " - " + empleadoActual.getDiasDeVacacionesRestantes()
					+ " - " + empleadoActual.isTieneSeguro() + " - " + empleadoActual.getEstaDePrueba() + " - "
					+ empleadoActual.getFechaInicio() + " - " + empleadoActual.getIsTrabajando() + " - "
					+ empleadoActual.getDiasTrabajados() + " - " + empleadoActual.getNumeroBultos() + " - "
					+ empleadoActual.getPesoCargado();
			 
			 return lineaActual;
			 
		}
	
		public void implementarDatosEmpleados() {
	
			try (BufferedReader lector = new BufferedReader(new FileReader(FICHERO_EMPLEADOS))) {
	
				String linea;
	
				while ((linea = lector.readLine()) != null) {
	
					String[] datosConstructor = linea.split(" - ");
	
					
					if (datosConstructor[5].equals("deTienda")) {
	
						reconstruirDeTienda(datosConstructor);
	
					} else if (datosConstructor[5].equals("deAlmacen")) {
	
						reconstruirDeAlmacen(datosConstructor);
	
					}
				}
				
			} catch (IOException e) {
				System.out.println("Error al cargar los empleados: " + e.getMessage());
			}
		}	
	
		
		private void reconstruirDeTienda(String[] datosConstructor) {
		    try {
		        mapaEmpleados.put(datosConstructor[0], new deTienda(datosConstructor));
		    } catch (Exception e) {
		        System.out.println("Error! : " + e.getMessage());
		    }
		}
	
		private void reconstruirDeAlmacen(String[] datosConstructor) {
		    try {
		    	
		        mapaEmpleados.put(datosConstructor[0], new deAlmacen(datosConstructor));
		        
		    } catch (Exception e) {
		        System.out.println("Error! : " + e.getMessage());
		    }
		}
	
	}