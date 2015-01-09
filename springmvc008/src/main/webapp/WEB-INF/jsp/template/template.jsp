<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>

<%-- Tags específicos para tiles --%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>


<tiles:importAttribute name="titulo"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale}" lang="${pageContext.response.locale}">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> <%--Forzar última versión de explorer --%>
	
	<title>			
		<spring:message code="${titulo}"  text="[No existe el texto '${titulo}']"/>
	</title>

	
	<%-- favicon --%>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/favicon.ico" />
	
	<%-- CSS --%>
	<link rel='stylesheet' type='text/css' media="screen" href='<c:url value="/resources/css/app/base.css"/>' />		
		
	<%-- JavaScript --%>			
	<script type='text/javascript' src='<c:url value="/resources/scripts/funciones.js" />' ></script>	

</head>

<body>
	<div id="wrapper">
	    <%-- ******************************************* --%>
		<%-- *************** CABECERA ****************** --%>
		<%-- ******************************************* --%>
		<tiles:insertAttribute name="cabecera" flush="true"/>

				
	
		<%-- ******************************************* --%>
		<%-- *************** CUERPO CENTRAL ************ --%>
		<%-- ******************************************* --%>
		<div id="content-wrapper" >	
			<h1><spring:message code="${titulo}"  text="[No existe el texto '${titulo}']"/></h1>					
			<tiles:insertAttribute name="contenido" flush="true"/>
		</div>



		<%-- ******************************************* --%>
		<%-- *************** PIE ****************** --%>
		<%-- ******************************************* --%>
		<div id="footer" class="fila">
			<tiles:insertAttribute name="pie" flush="true"/>
		</div>
		
	</div>
</body>

</html>