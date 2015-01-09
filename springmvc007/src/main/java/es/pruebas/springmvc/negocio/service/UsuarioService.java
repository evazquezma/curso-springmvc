package es.pruebas.springmvc.negocio.service;

import java.util.List;

import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.filtro.UsuarioFiltro;
import es.pruebas.springmvc.negocio.service.base.GenericService;

public interface UsuarioService extends GenericService<Usuario, Long>{

	public List<Usuario> buscar(UsuarioFiltro filtro);
}
