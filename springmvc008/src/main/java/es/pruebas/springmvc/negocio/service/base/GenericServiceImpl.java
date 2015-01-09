package es.pruebas.springmvc.negocio.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.pruebas.springmvc.dao.base.GenericRepository;
import es.pruebas.springmvc.dao.base.InstanceNotFoundException;




/**
 * Esta clase sirve de base a otros servicios - Contiene los métodos CRUD básicos.
 * Puede extenderse y crear tódos los metodos a medida necesarios para la lógica.
 *
 * <p>Para registrar esta clase en el contexto de Spring ha de utilizarse algo como:
 *
 *
 * <pre>
 *     &lt;bean id="userManager" class="com.miempresa.app.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.miempresa.app.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.miempresa.app.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 *
 * @author Indra
 * @param <T> Tipo de variable.
 * @param <PK> Tipo de clave primaria.
 */
@Transactional
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {
    /**
     * Instancia de {@link GenericDao}, Inicializada en el constructor de la clase.
     */
    protected GenericRepository<T, PK> genericDao;

    /**
     * Constructor de la clase.
     * @param genericDao Clase  de tipo {@link GenericDao} a utilizar.
     */
    public GenericServiceImpl(final GenericRepository<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
	public T find(PK id) throws InstanceNotFoundException {
        return genericDao.find(id);
    }

    @Override
	public boolean exists(PK id) {
        return genericDao.exists(id);
    }


	@Override
	public T save(T object) {
		return genericDao.save(object);
    }


    @Override
	public void remove(PK id) throws InstanceNotFoundException {
        genericDao.remove(id);
    }

	@Override
	public List<T> getAll() {
		return genericDao.getAll();
	}


}
