<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div id="dialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		<h3 id="modalLabel">&nbsp;</h3>
	</div>
	<div id="dialog-body" class="modal-body">
	
	</div>		
	<div class="modal-footer">
		<button class="btn" aria-hidden="true"><s:message code="close" /></button>
	</div>
</div>
<h2><s:message code="incident.incidents" /></h2>
<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="incident.id" /></th>
			<th><s:message code="incident.created" /></th>
			<% /* Should be type */ %>
			<th><s:message code="incident.description" /></th>
			<th><s:message code="incident.status" /></th>
			<th><s:message code="action" /></th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty incidents}">
			<tr>
				<td colspan="5"><p class="muted"><s:message code="noRecords" /></p></td>
			</tr>		
		</c:if>
		<c:forEach var="item" items="${incidents}">
			<tr>
				<td>${item.getId()}</td>
				<td>${item.getCreated()}</td>
				<td>${item.getIncidentType()}</td>
				<td>
					<s:message code="incident.status.${item.getStatus()}" />
				</td>
				<td>
					<div class="btn-group">
						<button class="btn dropdown-toggle" data-toggle="dropdown">
							<s:message code="action" /> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a data-id="${item.getId()}" data-modal-label='<s:message code="incident.details.dialog" />' href="#" class="open-IncidentDetailsDialog"><s:message code="details" /></a></li>
							<security:authorize access="!hasRole('ROLE_USER')">
								<li><a href='<c:url value="/incident/${item.getId()}/audit/create"/>' class=""><s:message code="incident.audit.add" /></a></li>
							</security:authorize>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<p class="lead">
	<a href='<c:url value="/incident/create"/>' class="btn btn-large btn-primary"><s:message code="incident.create" /></a></p> 
</p>

<script>
	$(document).on("click", ".open-IncidentDetailsDialog", function() {
		// clear
		$('#dialog-body').text("");
		// set dialog title		
		var label = $(this).data('modal-label');
		$('#modalLabel').text(label);
		// populate dialog data
		var id = $(this).data('id');
		var url = '<c:url value="/incident"/>' + "/" + id;
		$('#dialog-body').load(url);
		// show dialog
		$('#dialog').modal('show');
	});
</script>