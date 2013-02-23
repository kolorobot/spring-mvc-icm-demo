<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="signupForm">
	<h2 class="form-heading"><s:message code="signup.header" /></h2>
	<form:errors path="" element="p" class="text-error" />
	
	<s:message code="signup.name" var="name" />
	<label for="name">${name}</label>
	<form:input path="name" class="input-block-level"  />
	<form:errors path="name" element="p" class="text-error"/> 
	
	<s:message code="signup.email" var="email"/>
	<label for="email">${email}</label>
	<form:input path="email" class="input-block-level"  /> 
	<form:errors path="email" element="p" class="text-error"/>
	
	<s:message code="signup.phone" var="phone"/>
	<label for="email">${phone}</label>
	<form:input path="phone" class="input-block-level"  /> 
	<form:errors path="phone" element="p" class="text-error"/>  
	
	<s:message code="signup.password" var="password"/>
	<label for="password">${password}</label>
	<form:password path="password" class="input-block-level"  />
	<form:errors path="password" element="p" class="text-error"/> 
	
	<s:message code="signup.confirmPassword" var="confirmPassword"/>
	<label for="confirmPassword">${confirmPassword}</label>
	<form:password path="confirmedPassword" class="input-block-level"  />
	<form:errors path="confirmedPassword" element="p" class="text-error"/>
	
	<label for="role"><s:message code="signup.role" /></label>
	
	<select class="input-block-level" name="role">
		<s:message code="signup.role.user" var="roleUser"/>
		<option value="ROLE_USER">${roleUser}</option>
		<s:message code="signup.role.admin" var="roleAdmin"/>
		<option value="ROLE_ADMIN">${roleAdmin}</option>
		<s:message code="signup.role.employee" var="roleEmployee"/>
		<option value="ROLE_EMPLOYEE">${roleEmployee}</option>
	</select>
	
	<button class="btn btn-large btn-primary" type="submit"><s:message code="signup" /></button>
	<p class="form-text"><s:message code="signup.haveAccount" /> <a href='<s:url value="/signin"/>'><s:message code="signin" /></a></p>
</form:form>