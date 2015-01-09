<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>


<form:hidden path="usuario.id"/>

<j:texto path="usuario.nombre" label="etiqueta.usuario.nombre" obligatorio="true"/>
<br/>

<j:texto path="usuario.nif" label="etiqueta.usuario.nif" obligatorio="true"/>
<br/>

<j:texto path="usuario.login" label="etiqueta.usuario.login" obligatorio="true"/>
<br/>

<j:texto path="usuario.password" label="etiqueta.usuario.password" obligatorio="true"/>
<br/>

<j:texto path="usuario.fechaRegistro" label="etiqueta.usuario.fechaRegistro" obligatorio="true"/>
<br/>

<j:checkbox path="usuario.estaActivo" value="true" label="etiqueta.usuario.estaActivo"  posLabel="izda"/>
<br/>


<j:select path="usuario.tipo.id" label="etiqueta.usuario.tipo" items="${tiposUsuario}"
		itemLabel="descripciones['${pageContext.response.locale}']" itemValue="id" 
		textoDefault="seleccione" valorDefault=""/>
<br/>


<j:select path="usuario.roles" label="etiqueta.usuario.roles" items="${roles}"
		itemLabel="nombre" itemValue="id"/>
<br/>

