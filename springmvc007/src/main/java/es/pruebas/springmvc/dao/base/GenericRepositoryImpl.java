package es.pruebas.springmvc.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;





/**
 * Implementaci�n para Hibernate del repositorio gen�rico.
 * 
 * @author Emilio
 * 
 * @param <E>
 *            La entidad para la que se especializa el repositorio.
 * @param <PK>
 *            El tipo de la clave primaria de la entidad.
 */
public class GenericRepositoryImpl<E, PK extends Serializable> implements GenericRepository<E, PK> {

	private SessionFactory sessionFactory;

	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public GenericRepositoryImpl() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	/* (non-Javadoc)
	 * @see es.xunta.sede.basedatos.daos.GenericRepository#forzarSynch()
	 */
	@Override
	public void forzarSynch() {
		getSession().flush();
		getSession().clear();	
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.indra.infrastructure.repositories.GenericRepository#save(java.lang
	 * .Object)
	 */
	@SuppressWarnings("unchecked")
	public E save(E entity) {
		return (E) getSession().merge(entity);
		//getSession().saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.indra.infrastructure.repositories.GenericRepository#exists(java.io
	 * .Serializable)
	 */
	public boolean exists(PK id) {
		return getSession().createCriteria(entityClass)
				.add(Restrictions.idEq(id)).setProjection(Projections.id())
				.uniqueResult() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.indra.infrastructure.repositories.GenericRepository#find(java.io.
	 * Serializable)
	 */
	@SuppressWarnings("unchecked")
	public E find(PK id) throws InstanceNotFoundException {
		E entity = (E) getSession().get(entityClass, id);
		if (entity == null) {
			throw new InstanceNotFoundException(id, entityClass.getName());
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.indra.infrastructure.repositories.GenericRepository#remove(java.io
	 * .Serializable)
	 */
	public void remove(PK id) throws InstanceNotFoundException {
		getSession().delete(find(id));
	}


	@Override
	public List<E> getAll() {
		return getSession().createCriteria(entityClass).list();
	}


	@Override
	public List<E> searchByProperty(String campo, String value) {
		Criteria cri = getSession().createCriteria(entityClass);
		cri.add(Restrictions.eq(campo, value));
		return cri.list();
	}


	@Override
	public List<E> searchByProperties(List<String> campos, List<String> values) {
		Criteria cri = getSession().createCriteria(entityClass);
		for (int i = 0 ; i< campos.size() ;i++){
			cri.add(Restrictions.eq(campos.get(i), values.get(i)));
		}
		return cri.list();
	}
}
