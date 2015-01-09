package es.pruebas.springmvc.web.form.usuario;

import es.pruebas.springmvc.negocio.filtro.UsuarioFiltro;

public class BuscadorUsuariosForm {
	private final UsuarioFiltro filtro = new UsuarioFiltro();


	public UsuarioFiltro getFiltro() {
		return filtro;
	}
}
