<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>

<%-- Parámetros --%>
<%@ attribute name="label"     required="true"  description="Etiqueta del campo" type="java.lang.String"%>
<%@ attribute name="path"      required="true"  description="Campo que almacena el valor del select" type="java.lang.String"%>
<%@ attribute name="items"     required="false"  description="Campo que contiene la lista de elementos" type="java.util.Collection"%>
<%@ attribute name="itemLabel" required="true"  description="Propiedad del los elementos de la lista, que se va a mostrar" type="java.lang.String"%>
<%@ attribute name="itemValue" required="true"  description="Propiedad de los elementos de la lista, que servirá de clave" type="java.lang.String"%>
<%@ attribute name="editable"  required="false" description="Indica si el campo es editable" type="java.lang.Boolean"%>
<%@ attribute name="styleId"   required="false" description="Especifica la id para el componente" type="java.lang.String"%>

<%@ attribute name="obligatorio"      required="false"  description="Indica si el campo es obligatorio para poder destacarlo"%>
<%@ attribute name="styleClassInput"  required="false"  description="Clase CSS del input"%>
<%@ attribute name="styleClassLabel"  required="false"  description="Clase CSS del label"%>
<%@ attribute name="agregarVacio" 	  required="false"  description="Indica si es necesario agregar una entrada vacia al inicio"%>
<%@	attribute name="valorDefault" 	  required="false" description="Valor por defecto del combo" %>
<%@	attribute name="textoDefault" 	  required="false" description="Texto por defecto del combo" %>
<%@ attribute name="multiple" 		  required="false"  description="Indica si se permite seleccionar varias opciones"%>
<%@ attribute name="size"             required="false"  description="Tamaño del select"%>


<%-- --%>
<c:if test="${empty editable}">
	<c:set var="editable" value="true"/>
</c:if>

<c:if test="${empty obligatorio}">
	<c:set var="obligatorio" value="false"/>
</c:if>

<c:if test="${empty agregarVacio}">
	<c:set var="agregarVacio" value="false"/>
</c:if>



<%-- Cuerpo --%>
<label for="${styleId}" class="${styleClassLabel}">
	<spring:message code="${label}" text="[No existe el texto '${label}']" />
	
	<c:if test="${obligatorio and editable}">		
		<span class="obligatorio">*</span>		
	</c:if>
	
	<c:if test="${! obligatorio or ! editable}">
		<span class="noObligatorio">&nbsp;</span>
	</c:if>	
</label>


<c:if test="${empty styleId}">
	<c:set var="styleId" value="${path}"/>
</c:if>


<form:select id="${styleId}" path="${path}" cssClass= "${styleClassInput}"  multiple="${multiple}" size="${size}" onchange="${onchange}" disabled="${not editable}">
	
	<c:if test="${agregarVacio}">
		<form:option value="" label="" />
	</c:if>
	
	<c:if test="${!empty textoDefault}">
		<form:option selected="selected" value="${valorDefault}"><spring:message code="${textoDefault}" text="[No existe el texto '${textoDefault}']" /></form:option>
	</c:if>
	
	<form:options items="${items}" itemLabel="${itemLabel}" itemValue="${itemValue}" />
</form:select>


<c:if test="${not editable}">
	<form:hidden path="${path}"/>
</c:if>

<form:errors path="${path}" cssClass="error" />