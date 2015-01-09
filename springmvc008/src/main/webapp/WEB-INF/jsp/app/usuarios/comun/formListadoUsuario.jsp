<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>


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
					<td><a href='<c:url value="/usuarios/${usuario.id}"/>'>${usuario.nombre}</a></td>
					<td>${usuario.nif}</td>
					<td>${usuario.estaActivo}</td>
				</tr>
			</c:forEach>							
		</tbody>
	</table>
</div>		