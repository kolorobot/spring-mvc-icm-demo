<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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