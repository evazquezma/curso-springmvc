package es.pruebas.springmvc.negocio.filtro;

import java.util.Date;

public class UsuarioFiltro {
	private String nombre;
	private Date fechaRegistroDesde;
	private Date fechaRegistroHasta;
	private Boolean estaActivo;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaRegistroDesde() {
		return fechaRegistroDesde;
	}

	public void setFechaRegistroDesde(Date fechaRegistroDesde) {
		this.fechaRegistroDesde = fechaRegistroDesde;
	}

	public Date getFechaRegistroHasta() {
		return fechaRegistroHasta;
	}

	public void setFechaRegistroHasta(Date fechaRegistroHasta) {
		this.fechaRegistroHasta = fechaRegistroHasta;
	}

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
	
}
