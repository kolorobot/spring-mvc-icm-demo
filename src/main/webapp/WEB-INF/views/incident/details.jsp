<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<p class="lead">Details</p>
<table class="table table-hover">
	<tbody>
		<tr>
			<th>Id</th>
			<td><c:out value="${incident.getId()}" /></td>
		</tr>		
		<tr>
			<th>Created</th>
			<td><c:out value="${incident.getCreated()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Type</th>
			<td><c:out value="${incident.getIncidentType()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Description</th>
			<td><c:out value="${incident.getDescription()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Incident address</th>
			<td><c:out value="${incident.getIncidentAddress()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Current status</th>
			<td><c:out value="${incident.getStatus()}" default="N/A" /></td>
		</tr>
		<tr>
			<th>Creator</th>
			<td>
				Name:  <strong><c:out value="${incident.getCreator().getName()}" default="N/A" /></strong><br/>
				Email: <strong><c:out value="${incident.getCreator().getEmail()}" default="N/A" /></strong>,<br/>
				Phone: <strong><c:out value="${incident.getCreator().getPhone()}" default="N/A" /></strong>
			</td>
		</tr>
		<tr>
			<th>Assigned to</th>
			<td>
				Name:  <strong><c:out value="${incident.getAssignee().getName()}" default="N/A" /></strong><br/>
				Email: <strong><c:out value="${incident.getAssignee().getEmail()}" default="N/A" /></strong>,<br/>
				Phone: <strong><c:out value="${incident.getAssignee().getPhone()}" default="N/A" /></strong>
			</td>
		</tr>
	</tbody>
</table>
<p class="lead">Audits</p>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Created</th>
			<th>Description</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty audits}">
			<tr>
				<td colspan="3"><p class="muted">No records found</p></td>
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
