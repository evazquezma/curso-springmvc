<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"      	uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale}" lang="${pageContext.response.locale}">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Usuarios</title>
</head>
<body>

	<h1>Nuevo usuario</h1>
	
	<form:form commandName="usuarioForm">
		<form:errors path="usuario.nombre"/>
		Nombre : <form:input path="usuario.nombre"/> <br/>
		
		<form:errors path="usuario.nif"/>		
		Nif : <form:input path="usuario.nif"/> <br/>
		<br/>
		
		<form:errors path="usuario.login"/>
		Login : <form:input path="usuario.login"/> <br/>
		
		<form:errors path="usuario.password"/>
		password : <form:input path="usuario.password"/> <br/>
		
		<form:errors path="usuario.fechaRegistro"/>
		fechaRegsitro : <form:input path="usuario.fechaRegistro"/> <br/>
		
		<form:errors path="usuario.estaActivo"/> 	
		Activo : <form:checkbox path="usuario.estaActivo" value="true"/> <br/>		
		<br/>
	
		<form:errors path="usuario.tipo.id"/>
		Tipo:		 	
		<form:select path="usuario.tipo.id">
			<form:option value="">Seleccione uno</form:option>
			<form:options items="${tiposUsuario}" itemLabel="descripciones['es']" itemValue="id"/>
		</form:select>
		<br/>
		
		<form:errors path="usuario.roles"/>
		Roles: <form:select path="usuario.roles" multiple="true" items="${roles}" itemLabel="nombre" itemValue="id"/>		
		<br/>
		
		
		<form:button name="accion" value="crear">Crear</form:button>
		<form:button name="accion" value="volver">Volver</form:button>

	</form:form>
	

</body>
</html>