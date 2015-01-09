package es.pruebas.springmvc.web.form.usuario;

import javax.validation.Valid;

import es.pruebas.springmvc.modelo.Usuario;

public class UsuarioForm {
	@Valid
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
