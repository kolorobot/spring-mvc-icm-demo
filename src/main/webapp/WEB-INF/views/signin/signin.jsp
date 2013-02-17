<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form class="form" action='<s:url value="/j_spring_security_check"/>' method="post">
	<h2 class="form-heading"><s:message code="signin.header" /></h2>
	<c:if test="${not empty param['error']}">
  		<div class="alert alert-error">
  			<s:message code="signin.error" />
  		</div>
  	</c:if>
	<input type="text" class="input-block-level" placeholder='<s:message code="signin.email" />' name="j_username" /> 
	<input type="password" class="input-block-level" placeholder='<s:message code="signin.password" />' name="j_password" />
	<label class="checkbox">
		<input type="checkbox" name="_spring_security_remember_me"><s:message code="signin.rememberMe" /></input>
	</label>
	<button class="btn btn-large btn-primary" type="submit"><s:message code="signin" /></button>
	<p class="form-text"><s:message code="signin.newMember" /> <a href='<s:url value="/signup"/>'><s:message code="signup" /></a></p>
</form>
