<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
			</a> 
			<a class="brand" href="#">ICM (${operatorId})</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href='<s:url value="/"></s:url>'><s:message code="header.home" /></a></li>										
					<security:authorize access="isAuthenticated()">						
						<form class="navbar-search pull-left form-search" action='<c:url value="/incident/search" />'>
							<input type="text" name="q" class="search-query" placeholder='<s:message code="header.search" />'>
							<button type="submit" class="btn btn-mini"><i class="icon-search"></i></button>
						</form>										
					</security:authorize>
				</ul>
				<ul class="nav pull-right">
					<security:authorize access="!isAuthenticated()">
						<li><a href='<s:url value="/signin"></s:url>'><s:message code="signin" /></a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li><a href='<s:url value="/logout"></s:url>'><s:message code="logout" /> (<security:authentication property="principal.username"/>)</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>