<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="alert alert-error alert-block">  
  <h4><s:message code="error.header" /></h4>  
  <p>[${rootCause}]</p>
  <!-- ${stackTraceAsString} -->
</div>