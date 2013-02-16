<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<p class="lead">Details</p>
<table class="table table-hover">
	<tbody>
		<tr>
			<th><s:message code="incident.id" /></th>
			<td><c:out value="${incident.getId()}" /></td>
		</tr>		
		<tr>
			<th><s:message code="incident.created" /></th>
			<td><c:out value="${incident.getCreated()}" default="N/A" /></td>
		</tr>
		<tr>
			<th><s:message code="incident.type" /></th>
			<td><c:out value="${incident.getIncidentType()}" default="N/A" /></td>
		</tr>
		<tr>
			<th><s:message code="incident.description" /></th>
			<td><c:out value="${incident.getDescription()}" default="N/A" /></td>
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
						N/A
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th><s:message code="incident.status" /></th>
			<td><c:out value="${incident.getStatus()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Creator</th>
			<td>
				<s:message code="incident.account.name" />:  <strong><c:out value="${incident.getCreator().getName()}" default="N/A" /></strong><br/>
				<s:message code="incident.account.email" />: <strong><c:out value="${incident.getCreator().getEmail()}" default="N/A" /></strong>,<br/>
				<s:message code="incident.account.phone" />: <strong><c:out value="${incident.getCreator().getPhone()}" default="N/A" /></strong>
			</td>
		</tr>
		<tr>
			<th>Assigned to</th>
			<td>
				<s:message code="incident.account.name" />:  <strong><c:out value="${incident.getAssignee().getName()}" default="N/A" /></strong><br/>
				<s:message code="incident.account.email" />: <strong><c:out value="${incident.getAssignee().getEmail()}" default="N/A" /></strong>,<br/>
				<s:message code="incident.account.phone" />: <strong><c:out value="${incident.getAssignee().getPhone()}" default="N/A" /></strong>
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
		</tr>
	</thead>
	<tbody>
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
			</tr>
		</c:forEach>
	</tbody>
</table>
