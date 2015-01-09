package es.pruebas.springmvc.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.springmvc.dao.UsuarioDao;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.filtro.UsuarioFiltro;
import es.pruebas.springmvc.negocio.service.base.GenericServiceImpl;


@Transactional
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService{

	@Autowired
	public UsuarioServiceImpl(UsuarioDao usuarioDao) {
		super(usuarioDao);
	}



	@Override
	public List<Usuario> buscar(UsuarioFiltro filtro) {
		return ((UsuarioDao)genericDao).buscar(filtro);
	}

}
