<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ICM (${operatorId})</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/core.css" />" rel="stylesheet" media="screen" />
	
	<tilesx:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	<c:forEach var="cssName" items="${styles}">
		<link type="text/css" href="<c:url value="/resources/css/${cssName}"/>" rel="stylesheet" media="screen" />
	</c:forEach>
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	
	<tiles:insertAttribute name="header"  defaultValue="" />
	<!-- Page content -->
	<div class="container">
		<c:if test="${not empty message}">
			<c:choose>
				<c:when test="${message.type == 'WARNING'}">
					<c:set value="" var="alertClass" />
				</c:when>
				<c:otherwise>
					<c:set value="alert-${fn:toLowerCase(message.type)}" var="alertClass" />
				</c:otherwise>
			</c:choose>
			<div class="alert ${alertClass}">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<s:message code="${message.message}" />
			</div>
		</c:if>
		<tiles:insertAttribute name="body" defaultValue="" />
	</div>
	<!-- End of page content -->
	<tiles:insertAttribute name="footer"  defaultValue="" />
</body>
</html>