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

	<h1>Listado usuarios</h1>
	

	<form:form commandName="buscadorUsuariosForm">
		<div class="buscador">
			Nombre: <form:input path="filtro.nombre"/> <br/>
			Fecha registro desde: <form:input path="filtro.fechaRegistroDesde"/>
		</div>


		<div class="resultado">		
			<table>
				<thead>
					<tr>
						<td>Nombre</td>
						<td>NIF</td>
						<td>Activo</td>
					</tr>						
				</thead>
				<tbody>				
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td>${usuario.nombre}</td>
							<td>${usuario.nif}</td>
							<td>${usuario.estaActivo}</td>
						</tr>
					</c:forEach>							
				</tbody>
			</table>
		</div>
		
		
		
		<br/>
		<form:button name="accion" value="buscar">Buscar</form:button>
		<form:button name="accion" value="crear">Crear</form:button>

	</form:form>
	

</body>
</html>