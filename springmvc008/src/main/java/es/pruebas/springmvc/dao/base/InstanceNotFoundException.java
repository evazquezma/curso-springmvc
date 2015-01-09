package es.pruebas.springmvc.dao.base;

/**
 * Esta excepción indica que una entidad determinada no existe en el
 * repositorio.
 * 
 * @author appena
 * 
 */
@SuppressWarnings("serial")
public class InstanceNotFoundException extends Exception {

	public InstanceNotFoundException(Object key, String className) {
		super(String.format("No se ha encontrado la clave %s de la clase %s", key, className));		
	}
}
