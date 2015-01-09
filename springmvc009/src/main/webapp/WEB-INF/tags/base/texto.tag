<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>


<%@ attribute name="label" required="false"  description="Etiqueta del campo"%>
<%@ attribute name="path"  required="true"  description="Indica la ruta dónde se guarda"%>


<%@ attribute name="editable" required="false"  description="Indica si el campo es editable"%>
<%@ attribute name="obligatorio" required="false"  description="Indica si el campo es obligatorio para poder destacarlo"%>
<%@ attribute name="longitud" required="false"  description="Define el tamaño maximo del input"%>
<%@ attribute name="styleClassLabel"  required="false"  description="Clase CSS del label"%>
<%@ attribute name="styleClassInput"  required="false"  description="Clase CSS del input"%>

<c:if test="${empty editable}">
	<c:set var="editable" value="true"/>
</c:if>

<c:if test="${empty obligatorio}">
	<c:set var="obligatorio" value="false"/>
</c:if>



<c:if test="${! empty label}">
	<label for="${path}" class="${styleClassLabel}">
		<spring:message code="${label}" text="[No existe el texto '${label}']" />
		
		<c:if test="${obligatorio and editable}">		
			<span class="obligatorio">*</span>		
		</c:if>
		
		<c:if test="${! obligatorio or ! editable}">
			<span class="noObligatorio">&nbsp;</span>
		</c:if>				
	</label>		
</c:if>


	
<form:input id="${styleId}" type="text" disabled="${!editable}" path="${path}" cssClass="${styleClassInput}" maxlength="${longitud}"/>
						
<form:errors path="${path}" cssClass="error" />