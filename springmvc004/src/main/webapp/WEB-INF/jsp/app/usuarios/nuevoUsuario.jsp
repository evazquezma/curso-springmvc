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
	

	


		<br/>
		<form:button name="accion" value="crear">Crear</form:button>
		<form:button name="accion" value="volver">Volver</form:button>

	</form:form>
	

</body>
</html>