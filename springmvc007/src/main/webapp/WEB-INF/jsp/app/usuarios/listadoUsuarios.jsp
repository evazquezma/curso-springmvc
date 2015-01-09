<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale}" lang="${pageContext.response.locale}">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Usuarios</title>
</head>
<body>

	<h1>Listado usuarios</h1>
	
	
	<c:if test="${!empty mensajeInfo}">
		<p>${mensajeInfo}</p>
	</c:if>
	
	
	<form:form commandName="buscadorUsuariosForm">
		

		<jsp:include page="./comun/formBuscarUsuario.jsp"/>


		<jsp:include page="./comun/formListadoUsuario.jsp"/>
		
					
		<form:button name="accion" value="buscar">Buscar</form:button>
		<form:button name="accion" value="crear">Crear</form:button>

	</form:form>
	

</body>
</html>