package es.pruebas.springmvc.negocio.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import es.pruebas.springmvc.dao.base.InstanceNotFoundException;




/**
 * Manager genérico que se comunica con {@link GenericDao} para realizar las acciones CRUD a los POJO's.
 *
 *
 * @param <T> Tipo de variable.
 * @param <PK> tipo de la clave primaria.
 */
public interface GenericService<T, PK extends Serializable> {

    /*
     * Método genérico utilizado para obtener todos los objetos de un tipo particular.
     * Obtiene todas las filas de la tabla a la que esta asociada el objeto.
     * @return Lista de objetos
     */
	List<T> getAll();

    /**
     * Método genérico que obtiene el objeto indicado por id. Lanza una excepción del tipo
     * {@link ObjectRetrievalFailureException} (Runtime Exception) si no encuentra tal objeto.
     *
     * @param id Id del objeto buscado.
     * @return Objeto solicitado
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T find(PK id) throws InstanceNotFoundException;

    /**
     * Comprueba si existe el objeto de tipo T usando el argumento 'id'.
     * @param id Id del elemento a comprobar.
     * @return True si existe, false en caso contrario.
     */
    boolean exists(PK id);

    /**
     * Método genérico utilizado para guardar un objeto - diferencia entre crear y actualizar.
     * @param object Objeto a guardar.
     */
	T save(T object);

    /**
     * Método genérico que elimina un objeto de 'id' indicado por parámetro.
     * @param id Id del elemento a eliminar.
     */
    void remove(PK id)throws InstanceNotFoundException;

}
