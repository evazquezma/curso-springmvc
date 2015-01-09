package es.pruebas.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.pruebas.springmvc.dao.base.GenericRepositoryImpl;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.filtro.UsuarioFiltro;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends GenericRepositoryImpl<Usuario, Long> implements UsuarioDao {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getAll() {
		return getSession()
				.createCriteria(Usuario.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();		
	}
	
	@Override
	public List<Usuario> buscar(UsuarioFiltro filtro) {
		Criteria cri = getSession().createCriteria(Usuario.class);
		
		if (! StringUtils.isEmpty(filtro.getNombre())) {
			cri.add(Restrictions.ilike("nombre", filtro.getNombre(), MatchMode.ANYWHERE));
		}
			
		if (filtro.getFechaRegistroDesde() != null) {
			cri.add(Restrictions.ge("fechaRegistro", filtro.getFechaRegistroDesde()));
		}
		
		if (filtro.getFechaRegistroHasta() != null) {
			cri.add(Restrictions.ge("fechaRegistro", filtro.getFechaRegistroHasta()));
		}
		
		if (Boolean.TRUE.equals(filtro.getEstaActivo())) {
			cri.add(Restrictions.eq("estaActivo", filtro.getEstaActivo()));
		}
		
		return cri.list();
	}


}
