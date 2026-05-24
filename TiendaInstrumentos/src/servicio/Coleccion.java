package servicio;

import java.util.ArrayList;

public class Coleccion<T> {

	private ArrayList<T> listaGenerica = new ArrayList<>();

	/*CLASE REALIZADA MEDIANTE TUTORIAL DE CANAL DE YOUTUBE: TodoCode*/
	 /**
     * Añade a la lista el elemento que decidamos
     *
     * @param T Elemento.
     * 
     * @return void
     */
	public void añadir(T elemento) {
		
		listaGenerica.add(elemento);
		
	}

	 /**
     * Elimina a la lista el elemento que decidamos
     *
     * @param T Elemento.
     * 
     * @return void
     */
	public void eliminar(T elemento) {
		
		listaGenerica.remove(elemento);
		
	}

	
	public ArrayList<T> getLista() {
		
		return listaGenerica;
		
	}

	 /**
     * Devolveremos la cantidad de objetos en una lista
     *
     * @param Ninguno
     * 
     * @return void
     */
	public int contar() {
		
		return listaGenerica.size();
		
	}
}