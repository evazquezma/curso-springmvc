package es.pruebas.springmvc.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.springmvc.dao.UsuarioDao;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.service.base.GenericServiceImpl;


@Transactional
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService{

	@Autowired
	public UsuarioServiceImpl(UsuarioDao usuarioDao) {
		super(usuarioDao);
	}

}
