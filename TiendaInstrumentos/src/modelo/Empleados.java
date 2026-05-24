package modelo;

import excepciones.DescansandoInvalidException;
import excepciones.DniLongitudInvalidaException;
import excepciones.TlfLongitudInvalidaException;
import excepciones.TrabajandoInvalidException;

/*
 * Clase abstracta que representa a un Empleado del sistema.
 * Contiene los datos y comportamientos comunes a todos los tipos de empleados.
 * Es extendida por deTienda y deAlmacen.
 *
 * @author Gonzalo Prada
 * @version 1.0
 */
public abstract class Empleados extends Personal implements Incrementable {

    protected boolean tieneSeguro = false;

    protected int diasDeVacacionesRestantes = 30;

    protected double salarioBruto = 800;

    protected boolean estaDePrueba = false;

    protected String fechaInicio = "xx-xx-xxxx";

    protected boolean trabajando = true;

    protected int diasTrabajados = 0;

    /*====================CONSTRUCTORES=======================================================*/

    public Empleados(String dni, String telefono, String nombre, String apellido, String direccion)
            throws DniLongitudInvalidaException, TlfLongitudInvalidaException {
        super(dni, telefono, nombre, apellido, direccion);
    }

    public Empleados() {
        super();
    }

	    /*======================     FIN DE LOS CONSTRUCTORES         =============================*/

    // Gets y sets
    public boolean isTieneSeguro() {
        return tieneSeguro;
    }

    public void setTieneSeguro(boolean tieneSeguro) {
        this.tieneSeguro = tieneSeguro;
    }

    public int getDiasDeVacacionesRestantes() {
        return diasDeVacacionesRestantes;
    }

    public void setDiasDeVacacionesRestantes(int diasDeVacacionesRestantes) {
        this.diasDeVacacionesRestantes = diasDeVacacionesRestantes;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    /**
     * Añade el salario bruto del empleado, si el valor introducido es menor que 800, se establece automaticamente a 800.
     *
     * @param salarioBruto El nuevo salario bruto del empleado.
     */
    public void setSalarioBruto(double salarioBruto) {
        if (salarioBruto < 800) {
            salarioBruto = 800;
        }
        this.salarioBruto = salarioBruto;
    }

    public boolean getEstaDePrueba() {
        return estaDePrueba;
    }

    public void setEstaDePrueba(boolean estaDePrueba) {
        this.estaDePrueba = estaDePrueba;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean getIsTrabajando() {
        return trabajando;
    }

    public void setTrabajando(boolean trabajando) {
        this.trabajando = trabajando;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    /**
     * Resta los dias de vacaciones indicados al empleado, si los dias a restar superan los disponibles, se ingresa a 0.
     *
     * @param dias Numero de dias de vacaciones a consumir.
     */
    public void restarVacaciones(int dias) {
        if (dias > getDiasDeVacacionesRestantes()) {
            setDiasDeVacacionesRestantes(0);
        } else {
            setDiasDeVacacionesRestantes(getDiasDeVacacionesRestantes() - dias);
        }
    }

    /**
     * Registra un empleado. Si el empleado ya estaba trabajando, lanza una excepcion.
     *
     * @throws TrabajandoInvalidException Si el empleado ya se encuentra trabajando.
     */
    public void ficharEntrada() throws TrabajandoInvalidException {
        if (getIsTrabajando()) {
            throw new TrabajandoInvalidException();
        } else {
            setTrabajando(true);
        }
    }

    /**
     * Registra la salida del empleado del trabajo e incrementa los dias trabajados. Si el empleado no estaba trabajando, lanza una excepcion.
     *
     * @throws DescansandoInvalidException Si el empleado no se encuentra trabajando.
     */
    public void ficharSalida() throws DescansandoInvalidException {
        if (!getIsTrabajando()) {
            throw new DescansandoInvalidException();
        } else {
            setTrabajando(false);
            setDiasTrabajados(getDiasTrabajados() + 1);
        }
    }

    
    
    /**
     * Incrementa el trabajo realizado por el empleado segun el tipo de empleado.
     *
     * @param valor El valor a incrementar.
     */
    public abstract void Incrementar(double valor);

    /**
     * Imprime una ficha del trabajo realizado por el empleado.
     */
    public abstract void trabajoRealizado();

    
    @Override public String toString() { 
    	
    	return super.toString() + " Empleados [Seguro=" + tieneSeguro + " | Vacaciones=" + diasDeVacacionesRestantes + 
    			" | Salario=$" + salarioBruto + " | EnPrueba=" + estaDePrueba + " | Inicio=" + fechaInicio + 
    			" | Trabajando=" + trabajando + " | DíasTrabajados=" + diasTrabajados + "]"; 
    	
    }
}
