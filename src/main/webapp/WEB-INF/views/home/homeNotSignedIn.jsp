<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
	<h1><s:message code="home.application.name" /></h1>
	<p><s:message code="home.signup.message" /></p>
	<p>
		<a href='<s:url value="/signup" />' class="btn btn-primary btn-large"> <s:message code="signup" /> </a>
	</p>
</div>