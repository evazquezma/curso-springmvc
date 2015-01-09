package es.pruebas.springmvc.dao.base;

import java.io.Serializable;
import java.util.List;


/**
 * Repositorio genérico para entidades. No está asociado a ninguna tecnología.
 * Contempla las principales operaciones contra un repositorio. Se parametriza
 * para la entidad correspondiente y para el tipo de clave primaria de la
 * entidad.
 * 
 * @author indra
 * 
 * @param <E>
 *            La entidad para la que se especializa el repositorio.
 * @param <PK>
 *            El tipo de la clave primaria de la entidad.
 */
public interface GenericRepository<E, PK extends Serializable> {
	/**
	 * Sincroniza la caché de hibernate con la base de datos
	 */
	void forzarSynch();
	
	
	/**
	 * Almacena o actualiza una instancia en el repositorio.
	 * 
	 * @param entity
	 *            El objeto a salvar.
	 */
	E save(E entity);

	/**
	 * Localiza una entidad por su clave primaria.
	 * 
	 * @param id
	 *            La clave de la entidad.
	 * @return La entidad buscada.
	 * @throws InstanceNotFoundException
	 *             Si la entidad no existe en el repositorio.
	 */
	E find(PK id) throws InstanceNotFoundException;

	/**
	 * Comprueba si una entidad existe en el repositorio.
	 * 
	 * @param id
	 *            La clave de la entidad.
	 * @return true si la entidad existe, false en caso contrario.
	 */
	boolean exists(PK id);

	/**
	 * Elimina una entidad del repositorio.
	 * 
	 * @param id
	 *            La clave de la entidad.
	 * @throws InstanceNotFoundException
	 *             Si la entidad no existe en el repositorio.
	 */
	void remove(PK id) throws InstanceNotFoundException;
	
	/**
	 * Obtiene todas las entidades
	 * 
	 * @return
	 */
	List<E> getAll();
	
	/**
	 * Devuelve todas las entidades filtrando por un valor de un campo
	 * 
	 * @param campo
	 * @param value
	 * @return
	 */
	List<E> searchByProperty(String campo, String value);
	
	
	/**
	 * Devuelve todas las entidades filtrando por una lista de valores y campos
	 * @param campo
	 * @param value
	 * @return
	 */
	List<E> searchByProperties(List<String> campo, List<String> value);
	
}
