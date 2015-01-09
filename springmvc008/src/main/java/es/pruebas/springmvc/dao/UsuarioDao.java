package es.pruebas.springmvc.dao;

import java.util.List;

import es.pruebas.springmvc.dao.base.GenericRepository;
import es.pruebas.springmvc.modelo.Usuario;
import es.pruebas.springmvc.negocio.filtro.UsuarioFiltro;

public interface UsuarioDao extends GenericRepository<Usuario, Long> {
	public List<Usuario> buscar(UsuarioFiltro filtro);
}
