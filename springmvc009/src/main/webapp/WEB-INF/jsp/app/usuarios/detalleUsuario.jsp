<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>

	
<form:form commandName="usuarioForm">

	<jsp:include page="./comun/formUsuario.jsp"/>
				
				
	<div class="botonera">	
		<form:button name="accion" value="actualizar">Actualizar</form:button>
		<form:button name="accion" value="eliminar">Eliminar</form:button>
		<form:button name="accion" value="volver">Volver</form:button>
	</div>
</form:form>	
