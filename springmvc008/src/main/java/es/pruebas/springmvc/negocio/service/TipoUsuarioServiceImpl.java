package es.pruebas.springmvc.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.springmvc.dao.TipoUsuarioDao;
import es.pruebas.springmvc.modelo.TipoUsuario;
import es.pruebas.springmvc.negocio.service.base.GenericServiceImpl;


@Transactional
@Service("tipoUsuarioService")
public class TipoUsuarioServiceImpl extends GenericServiceImpl<TipoUsuario, Long> implements TipoUsuarioService{

	@Autowired
	public TipoUsuarioServiceImpl(TipoUsuarioDao tipoUsuarioDao) {
		super(tipoUsuarioDao);
	}		

}
