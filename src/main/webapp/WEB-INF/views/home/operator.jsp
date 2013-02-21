<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form class="form" action='<s:url value="/operator"/>' method="post">
	<h2 class="form-heading"><s:message code="operator.header" /></h2>
	<input type="text" class="input-block-level" placeholder='<s:message code="operator.id" />' name="id" /> 
	<button class="btn btn-large btn-primary" type="submit"><s:message code="submit" /></button>	
</form>