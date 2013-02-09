<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="incidentForm">
	<h2 class="form-heading">Create incident</h2>
	<form:errors path="" element="p" class="text-error" />
	
	<form:input path="type" class="input-block-level" placeholder="Incident type" />
	<form:errors path="type" element="p" class="text-error"/> 
	
		
	<form:input path="address" class="input-block-level" placeholder="Incident address" />
	<form:errors path="address" element="p" class="text-error"/> 

	<form:textarea path="description" class="input-block-level" placeholder="Incident description" rows="5" />
	<form:errors path="description" element="p" class="text-error"/> 
	
	<button class="btn btn-large btn-primary" type="submit">Create</button>	
</form:form>