<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="signupForm">
	<h2 class="form-heading"><s:message code="signup.header" /></h2>
	<form:errors path="" element="p" class="text-error" />
	<s:message code="signup.name" var="name" />
	<form:input path="name" class="input-block-level" placeholder='${name}' />
	<form:errors path="name" element="p" class="text-error"/> 
	<s:message code="signup.email" var="email"/>
	<form:input path="email" class="input-block-level" placeholder='${email}' /> 
	<form:errors path="email" element="p" class="text-error"/> 
	<s:message code="signup.password" var="password"/>
	<form:password path="password" class="input-block-level" placeholder='${password}' />
	<form:errors path="password" element="p" class="text-error"/> 
	<s:message code="signup.confirmPassword" var="confirmPassword"/>
	<form:password path="confirmedPassword" class="input-block-level" placeholder='${confirmPassword}' />
	<form:errors path="confirmedPassword" element="p" class="text-error"/>
	<button class="btn btn-large btn-primary" type="submit"><s:message code="signup" /></button>
	<p class="form-text"><s:message code="signup.haveAccount" /> <a href='<s:url value="/signin"/>'><s:message code="signin" /></a></p>
</form:form>