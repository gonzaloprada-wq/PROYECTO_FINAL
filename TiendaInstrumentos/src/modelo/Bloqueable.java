package modelo;

import excepciones.ProveedorBloqueadoException;

public interface Bloqueable {

	void Bloquear() throws ProveedorBloqueadoException;
	
}
