package es.pruebas.springmvc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoUsuario> getAll() {
		return getSession()
				.createCriteria(TipoUsuario.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();		
	}

}
