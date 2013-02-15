<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form class="form" method="post" modelAttribute="auditForm">
	<h2 class="form-heading">Create audit</h2>
	<form:errors path="" element="p" class="text-error" />
	
	<form:select path="status" items="${auditForm.getAvailableStatuses()}" />
	<form:errors path="status" element="p" class="text-error"/> 
	
	<form:textarea path="description" class="input-block-level" placeholder="Audit details" rows="5" />
	<form:errors path="description" element="p" class="text-error"/> 
	
	<button class="btn btn-large btn-primary" type="submit">Create</button>	
</form:form>