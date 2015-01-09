<%@ include file="/WEB-INF/jsp/comun/directivas.jsp"%>

<%-- Parámetros --%>

<%-- Tag que permite definir un nombre de campo y un check (editable o no). 
		- edit: Indicador si el campo es editable o no
		- onclick: Codigo que se va a ejecutar en el evento onclick (cuando se pulsa sobre el check)
		- name: Nombre de la propiedad del Form que se va a mostrar. Este valor tambien se usa como id del campo
		- value: Valor transmitido si se checkea
		- obligatorio: Indicador de si el campo del formulario es obligatorio
		- styleClassLabel: Clase del estilo que se va a aplicar al nombre del campo. 
		- styleClassData: Clase del estilo aplicado a la celda  que contiene el valor del campo. 

 --%>
<%@ attribute name="label"  required="false"  description="Etiqueta del campo" type="java.lang.String"%>
<%@ attribute name="path"  required="true"  description="Campo que almacena el valor del checkbox" type="java.lang.String"%>
<%@attribute name="editable" required="false" rtexprvalue="true" description="Indicador si el campo es editable o no"%>
<%@attribute name="visible" required="false" description="Indicador de si el buscador es visible o no"%>
<%@attribute name="name" required="false" description="Nombre de la propiedad del Form que se va a mostrar. Este valor tambien se usa como id del campo"%>
<%@attribute name="value" required="true" description="Valor transmitido si se checkea"%>
<%@attribute name="obligatorio"  required="false" description="Indicador de si el campo del formulario es obligatorio" %>
<%@attribute name="styleId" required="false" description="Id que se le dará al elemento. Por defecto será el mismo que el atributo name"%>
<%@attribute name="styleClass" required="false" description="Clase del estilo que se va a aplicar al radio buton, cuando es editable"%>
<%@attribute name="styleClassLabel" required="false" description="Estilo para la celda que contiene el label." %>
<%@attribute name="styleClassData"  required="false"  description="Estilo para la celda que contiene el texto. "%>
<%@attribute name="posLabel"  required="false"  description="Posición del lable, si a la dcha o izda del check. Valores dcha,izda" %>
<%@attribute name="showError" required="false"  description="Indicador si se quiere mostrar el error o no"%>
<%@ attribute name="onchange" required="false" description="Javascript onchange"%>


<c:if test="${empty editable}">
	<c:set var="editable" value="true"/>
</c:if>

<c:if test="${empty posLabel}">
	<c:set var="posLabel" value="dcha"/>
</c:if>

<c:if test="${! empty styleId}">
	<c:set var="labelFor" value="${styleId}"/>
</c:if>






<%-- Cuerpo --%> 
<c:if test="${posLabel == 'izda'}">
	<label for="${labelFor}">
		<spring:message code="${label}" text="[No existe el texto '${label}']" />
		
		<c:if test="${obligatorio and editable}">		
			<span class="obligatorio">*</span>		
		</c:if>
		
		<c:if test="${! obligatorio or ! editable}">
			<span class="noObligatorio">&nbsp;</span>
		</c:if>			
	</label>
</c:if>


<form:checkbox path="${path}"  value="${value}" id="${styleId}" class="${styleClass}" onchange="${onchange}"/>


<%-- Cuerpo --%>
<c:if test="${posLabel == 'dcha'}">
	<label for="${labelFor}">
		<spring:message code="${label}" text="[No existe el texto '${label}']" />
	</label>
		
	<c:if test="${obligatorio and editable}">		
		<span class="obligatorio">*</span>		
	</c:if>
	
	<c:if test="${! obligatorio or ! editable}">
		<span class="noObligatorio">&nbsp;</span>
	</c:if>		
</c:if>



<%-- <c:if test="${empty showError}"> --%>
<%-- 	<form:errors path="${path}" cssClass="error" /> --%>
<%-- </c:if> --%>


