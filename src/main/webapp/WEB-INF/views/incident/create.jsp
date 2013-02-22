<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="incidentForm">
	<h2 class="form-heading"><s:message code="incident.create" /></h2>
	<form:errors path="" element="p" class="text-error" />
	
	<label for="type"><s:message code="incident.type" /></label>
	<form:input path="type" class="input-block-level" />
	<form:errors path="type" element="p" class="text-error"/> 
	
	<label for="description"><s:message code="incident.description" /></label>
	<form:textarea path="description" class="input-block-level" rows="5" />
	<form:errors path="description" element="p" class="text-error"/> 
	
	<label for="addressLine"><s:message code="incident.addressLine" /></label>
	<s:message code="incident.addressLine" var="addressLine" />
	<form:input path="addressLine" class="input-block-level" />
	<form:errors path="addressLine" element="p" class="text-error"/>
	
	<label for="cityLine"><s:message code="incident.cityLine" /></label>
	<s:message code="incident.cityLine" var="cityLine" />
	<form:input path="cityLine" class="input-block-level" />
	<form:errors path="cityLine" element="p" class="text-error"/> 
	
	<button class="btn btn-large btn-primary" type="submit"><s:message code="incident.create.submit" /></button>	
</form:form>