package es.pruebas.springmvc.modelo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyClass;
import javax.persistence.Table;

@Table
@Entity(name="TIPOUSUARIO")
public class TipoUsuario {

	@Id @GeneratedValue
	private Long id;

	private String codigo;
	
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "TIPOUSUARIO_I18N")
	@MapKeyClass(String.class)
	private Map<String, String> descripciones = new HashMap<String, String>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Map<String, String> getDescripciones() {
		return descripciones;
	}


	public void setDescripciones(Map<String, String> descripciones) {
		this.descripciones = descripciones;
	}

}
