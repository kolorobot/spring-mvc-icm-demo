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
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<h2>Incidents</h2>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Created</th>
			<th>Incident type</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty incidents}">
			<tr>
				<td colspan="5"><p class="muted">No records found</p></td>
			</tr>		
		</c:if>
		<c:forEach var="item" items="${incidents}">
			<tr>
				<td>${item.getId()}</td>
				<td>${item.getCreated()}</td>
				<td>${item.getIncidentType()}</td>
				<td>${item.getStatus()}</td>
				<td>
					<div class="btn-group">
						<button class="btn dropdown-toggle" data-toggle="dropdown">
							Action <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a data-id="${item.getId()}" data-modal-label="Incident details" href="#" class="open-IncidentDetailsDialog">View Details</a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li><a data-id="${item.getId()}" href="#" class="">Add Audit</a></li>
							</security:authorize>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<p class="lead">
	<a href='<c:url value="/incident/create"/>' class="btn btn-large btn-primary">Create Incident</a></p> 
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