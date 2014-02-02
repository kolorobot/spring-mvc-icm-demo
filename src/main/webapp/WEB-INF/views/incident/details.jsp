<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<p class="lead"><s:message code="details" /></p>
<table class="table table-hover">
	<tbody>
		<tr>
			<th><s:message code="incident.id" /></th>
			<td><c:out value="${incident.getId()}" /></td>
		</tr>		
		<tr>
			<th><s:message code="incident.created" /></th>
			<td><c:out value="${incident.getCreated()}" default="-" /></td>
		</tr>
		<tr>
			<th><s:message code="incident.type" /></th>
			<td><c:out value="${incident.getIncidentType()}" default="-" /></td>
		</tr>
		<tr>
			<th><s:message code="incident.description" /></th>
			<td><c:out value="${incident.getDescription()}" default="-" /></td>
		</tr>
		<tr>
			<th><s:message code="incident.address" /></th>
			<td>
				<c:choose>
					<c:when test="${incident.getAddress() != null}">
						<c:out value="${incident.getAddress().getAddressLine()}" /> <br/>					
						<c:out value="${incident.getAddress().getCityLine()}" />
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th><s:message code="incident.status" /></th>
			<td>
				<s:message code="incident.status.${incident.getStatus()}" />
			</td>
		</tr>
		<tr>
			<th><s:message code="incident.creator" /></th>
			<td>
				<s:message code="incident.account.name" />:  <strong><c:out value="${incidentCreator.getName()}" default="-" /></strong><br/>
				<s:message code="incident.account.email" />: <strong><c:out value="${incidentCreator.getEmail()}" default="-" /></strong><br/>
				<s:message code="incident.account.phone" />: <strong></strong>
			</td>
		</tr>
		<tr>
			<th><s:message code="incident.assignedTo" /></th>
			<td>
				<s:message code="incident.account.name" />:  <strong><c:out value="${incidentAssignee.getName()}" default="" /></strong><br/>
				<s:message code="incident.account.email" />: <strong><c:out value="${incidentAssignee.getEmail()}" default="" /></strong><br/>
				<s:message code="incident.account.phone" />: <strong><c:out value="${incidentAssignee.getPhone()}" default="-" /></strong>
			</td>
		</tr>
	</tbody>
</table>
<p class="lead"><s:message code="incident.audits" /></p>
<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="incident.audit.id" /></th>
			<th><s:message code="incident.audit.created" /></th>
			<th><s:message code="incident.audit.description" /></th>
			<th><s:message code="incident.status.change" /></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="audits" value="${audits}" />
		<c:if test="${empty audits}">
			<tr>
				<td colspan="3"><p class="muted"><s:message code="noRecords" /></p></td>
			</tr>		
		</c:if>
		<c:forEach var="item" items="${audits}">
			<tr>
				<td>${item.getId()}</td>
				<td>${item.getCreated()}</td>
				<td>${item.getDescription()}</td>
				<td>
					<s:message code="incident.status.${item.getPreviousStatus()}" var="previousStatus"/> 
					<s:message code="incident.status.${item.getStatus()}" var="currentStatus"/>
					<s:message code="incident.status.change.message" arguments="${previousStatus}, ${currentStatus}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
