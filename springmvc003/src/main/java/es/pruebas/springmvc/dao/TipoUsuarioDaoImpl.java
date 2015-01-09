package es.pruebas.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.springmvc.dao.base.GenericRepositoryImpl;
import es.pruebas.springmvc.modelo.TipoUsuario;

@Repository("tipoUsuarioDao")
public class TipoUsuarioDaoImpl extends GenericRepositoryImpl<TipoUsuario, Long> implements TipoUsuarioDao {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


}
