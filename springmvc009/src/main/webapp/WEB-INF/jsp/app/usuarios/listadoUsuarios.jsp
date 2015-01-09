<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>

<c:if test="${!empty mensajeInfo}">
	<p>${mensajeInfo}</p>
</c:if>


<form:form commandName="buscadorUsuariosForm">
	

	<jsp:include page="./comun/formBuscarUsuario.jsp"/>


	<jsp:include page="./comun/formListadoUsuario.jsp"/>
	
				
	<form:button name="accion" value="buscar">Buscar</form:button>
	<form:button name="accion" value="crear">Crear</form:button>

</form:form>