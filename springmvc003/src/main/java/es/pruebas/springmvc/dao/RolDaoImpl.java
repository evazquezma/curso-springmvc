package es.pruebas.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.springmvc.dao.base.GenericRepositoryImpl;
import es.pruebas.springmvc.modelo.Rol;

@Repository("rolDao")
public class RolDaoImpl extends GenericRepositoryImpl<Rol, Long> implements RolDao {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


}
