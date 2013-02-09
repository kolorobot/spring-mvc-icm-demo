<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<dl class="dl-horizontal">
	<dt>Id</dt>
	<dd>
		<c:out value="${incident.getId()}"></c:out>
	</dd>
	
	<dt>Created</dt>
	<dd>
		<c:out value="${incident.getCreated()}" default="N/A"></c:out>
	</dd>
	
	<dt>Type</dt>
	<dd>
		<c:out value="${incident.getIncidentType()}" default="N/A"></c:out>
	</dd>
	
	<dt>Description</dt>
	<dd>
		<c:out value="${incident.getDescription()}" default="N/A"></c:out>
	</dd>
	
	<dt>Incident address</dt>
	<dd>
		<c:out value="${incident.getIncidentAddress()}" default="N/A"></c:out>
	</dd>
	
	<dt>Current status</dt>
	<dd>
		<c:out value="${incident.getStatus()}" default="N/A"></c:out>
	</dd>
	
	<dt>Creator</dt>
	<dd>
		<c:out value="${incident.getCreator().getName()}" default="N/A" />
		<strong>Email:</strong> <c:out value="${incident.getCreator().getEmail()}" default="N/A" />,
		<strong>Phone:</strong> <c:out value="${incident.getCreator().getPhone()}" default="N/A" />
	</dd>
	
	<dt>Assigned to</dt>
	<dd>
		<c:out value="${incident.getAssignee().getName()}" default="N/A" />
		<strong>Email:</strong> <c:out value="${incident.getAssignee().getEmail()}" default="N/A" />,
		<strong>Phone:</strong> <c:out value="${incident.getAssignee().getPhone()}" default="N/A" />
	</dd>
</dl>

